package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.application;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.Measurement;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingDataRepository;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.CreateDataRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck.NullSensingDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensingDataService {

    private final SensingDataRepository sensingDataRepository;

    @Autowired
    private InfluxDBClient influxDBClient;

    /*
     * 데이터 저장
     */
    @Transactional
    public void save(final CreateDataRequest request) {

        final var requestData = request.getDataList();

        final List<SensingData> dataList = requestData.stream()
                .map(data -> SensingData.builder()
                        .sensingValue(data.value())
                        .measurement(Measurement.valueOf(data.measurement()))
                        .build())
                .toList();

        sensingDataRepository.saveAll(dataList);
    }

    /* 데이터 값 수정 */
    @Transactional
    public void update(final Long dataId, final Double value) {

        final SensingData sensingData = sensingDataRepository.findById(dataId)
                .orElseThrow(NullSensingDataException::new);

        sensingData.update(value);
    }


    // measurement 필터링 조건 생성
    private String getMeasurementFilterClause(String[] measurements) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < measurements.length; i++) {
            if (i != 0) {
                sb.append(" or ");
            }
            sb.append("r._measurement ==").append("\"").append(measurements[i]).append("\"");
        }
        return sb.toString();
    }


    public List<Map<String, Object>> queryData() {
        final String influxOrg = "belab";
        final String influxBucket = "belab";

        // 쿼리 실행
        final String[] measurements = {"Thermostat_Amb_Humidity", "Thermostat_Amb_Temperature", "Irradi1", "Irradi2"};
        final String query = String.format("from(bucket:\"%s\") |> range(start: -1h) |> filter(fn: (r) => %s)", influxBucket, getMeasurementFilterClause(measurements));

        final QueryApi queryApi = influxDBClient.getQueryApi();
        final List<FluxTable> queryResult = queryApi.query(query, influxOrg);

        return queryResult.stream()
                .flatMap(table -> table.getRecords().stream())
                .map(record -> {
                    final String measurement = record.getMeasurement();
                    final String value = record.getValue().toString();
                    final String time = record.getTime().toString();

                    Map<String, Object> result = new HashMap<>();
                    result.put("measurement", measurement);
                    result.put("value", value);
                    result.put("time", time);

                    return result;
                })
                .collect(Collectors.toList());
    }
}
