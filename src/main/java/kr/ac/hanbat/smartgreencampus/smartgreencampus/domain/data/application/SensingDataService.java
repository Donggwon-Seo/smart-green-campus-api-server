package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.application;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.Measurement;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingDataRepository;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.CreateDataRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck.NullSensingDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensingDataService {

    private final SensingDataRepository sensingDataRepository;

    /*
     * 데이터 저장
     */
    @Transactional
    public Long save(final CreateDataRequest request) {

        final SensingData sensingData = SensingData.builder()
                .sensingValue(request.value())
                .measurement(Measurement.of(request.measurement()))
                .build();

        sensingDataRepository.save(sensingData);
        log.info("measurement : {}, value : {}", request.measurement(), request.value());

        return sensingData.getId();
    }

    /* 데이터 값 수정 */
    @Transactional
    public void update(final Long dataId, final Double value) {

        final SensingData sensingData = sensingDataRepository.findById(dataId)
                .orElseThrow(NullSensingDataException::new);

        sensingData.update(value);
    }
}
