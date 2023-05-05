package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.application.SensingDataService;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApi;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApiFailWithoutAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Tag(name = "센싱 데이터")
@RestController
@RequiredArgsConstructor
public class SensingDataController {

    private final SensingDataService sensingDataService;

    @SwaggerApi(summary = "데이터 목록 조회", implementation = Result.class)
    @SwaggerApiFailWithoutAuth
    @GetMapping("/api/data")
    public Result data() {

        return new Result(sensingDataService.findAllByMember().stream()
                .map(data -> new DataDto(data))
                .collect(Collectors.toList()));
    }


    @SwaggerApi(summary = "특정 종류의 데이터 목록 조회", implementation = Result.class)
    @SwaggerApiFailWithoutAuth
    @GetMapping("/api/data/kinds")
    public Result data(@RequestBody @Valid final SensingDataByKindRequest request) {

        return new Result(sensingDataService.findAllByMemberKind(request).stream()
                .map(data -> new DataDto(data))
                .collect(Collectors.toList()));
    }


    @SwaggerApi(summary = "데이터 등록", implementation = CreateDataResponse.class)
    @SwaggerApiFailWithoutAuth
    @PostMapping("/api/data")
    public CreateDataResponse saveData(@RequestBody @Valid final CreateDataRequest request) {
        return new CreateDataResponse(sensingDataService.save(request));
    }


    @SwaggerApi(summary = "데이터 수정", implementation = UpdateDataResponse.class)
    @SwaggerApiFailWithoutAuth
    @PatchMapping("/api/data/{id}")
    public UpdateDataResponse updateDataResponse(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final UpdateDataRequest request) {

        sensingDataService.update(id, request.value());
        final SensingData sensingData = sensingDataService.findById(id);

        return new UpdateDataResponse(sensingData.getId());
    }
}
