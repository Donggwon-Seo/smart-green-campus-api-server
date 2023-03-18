package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller;

import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Location;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingKind;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.service.SensingDataService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SensingDataController {

    private final SensingDataService sensingDataService;

    /* 데이터 조회 */
    @GetMapping("/api/data")
    public Result data() {

        List<SensingData> sensingDataList = sensingDataService.findAllByMember();

        List<DataDto> dataDtoList = sensingDataList.stream()
                .map(data -> new DataDto(data))
                .collect(Collectors.toList());

        return new Result(dataDtoList);
    }


    /* 데이터 등록 */
    @PostMapping("/api/data")
    public CreateDataResponse saveData(@RequestBody @Valid CreateDataRequest request) {

        SensingKind kind = SensingKind.transform(request.sensingKind);
        Location location = Location.createLocation(request.building, request.details);

        Long id = sensingDataService.save(request.memberId, request.name, request.value, kind, location);
        return new CreateDataResponse(id);
    }

    /* 데이터 수정 */
    @PatchMapping("/api/data/{id}")
    public UpdateDataResponse updateDataResponse(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateDataRequest request) {

        sensingDataService.update(id, request.value);
        SensingData sensingData = sensingDataService.findById(id);

        return new UpdateDataResponse(sensingData.getId());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T contents;
    }

    @Data
    @AllArgsConstructor
    static class DataDto {
        private String name;
        private Double value;
        private String kind;
        private String building; //Location
        private String details;  //Location
        private String maker;

        public DataDto(SensingData data) {
            this.name = data.getName();
            this.value = data.getSensingValue();
            this.kind = String.valueOf(data.getKind());
            this.building = data.getLocation().getBuilding();
            this.details = data.getLocation().getDetails();
            this.maker = data.getMember().getName();
        }
    }

    @Data
    @AllArgsConstructor
    static class UpdateDataResponse {
        private Long id;
    }

    @Data
    static class UpdateDataRequest {
        private Long dataId;
        private Double value;
    }

    @Data
    @AllArgsConstructor
    static class CreateDataResponse {
        private Long id;
    }

    @Data
    static class CreateDataRequest {
        private Long memberId;
        private String name;
        private Double value;
        private String sensingKind;
        private String building; //Location
        private String details;  //Location
    }
}
