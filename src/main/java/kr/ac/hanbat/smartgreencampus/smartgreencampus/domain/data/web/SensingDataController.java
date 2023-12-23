package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.application.SensingDataService;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingDataRepository;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApi;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApiFailWithoutAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "센싱 데이터")
@RestController
@RequiredArgsConstructor
public class SensingDataController {


    private final SensingDataService sensingDataService;
    private final SensingDataRepository sensingDataRepository;


    @SwaggerApi(summary = "데이터 단건 조회", implementation = Result.class)
    @SwaggerApiFailWithoutAuth
    @GetMapping("/api/data/{id}")
    public Result data(@PathVariable final Long id) {

        SensingData sensingData = sensingDataRepository.findById(id).orElseThrow();
        return new Result(new DataDto(sensingData));
    }

    @SwaggerApi(summary = "데이터 목록 조회(influxDB)", implementation = Result.class)
    @SwaggerApiFailWithoutAuth
    @GetMapping("/api/data")
    public List<Map<String, Object>> getData() {
        return sensingDataService.queryData();
    }

    @SwaggerApi(summary = "데이터 등록")
    @SwaggerApiFailWithoutAuth
    @PostMapping("/api/data")
    public void saveData(@RequestBody @Valid final CreateDataRequest request) {
        sensingDataService.save(request);
    }


    @SwaggerApi(summary = "데이터 수정", implementation = UpdateDataResponse.class)
    @SwaggerApiFailWithoutAuth
    @PatchMapping("/api/data/{id}")
    public UpdateDataResponse updateDataResponse(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final UpdateDataRequest request) {

        sensingDataService.update(id, request.value());
        final SensingData sensingData = sensingDataRepository.findById(id)
                .orElseThrow();

        return new UpdateDataResponse(sensingData.getId());
    }


}
