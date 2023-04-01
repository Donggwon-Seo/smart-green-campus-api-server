package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller;

import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.sensingdata.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Location;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingKind;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.service.SensingDataService;
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
    public CreateDataResponse saveData(@RequestBody @Valid final CreateDataRequest request) {

        SensingKind kind = SensingKind.transform(request.getSensingKind());
        Location location = Location.createLocation(request.getBuilding(), request.getDetails());

        Long id = sensingDataService.save(request.getMemberId(), request.getName(), request.getValue(), kind, location);
        return new CreateDataResponse(id);
    }

    /* 데이터 수정 */
    @PatchMapping("/api/data/{id}")
    public UpdateDataResponse updateDataResponse(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final UpdateDataRequest request) {

        sensingDataService.update(id, request.getValue());
        SensingData sensingData = sensingDataService.findById(id);

        return new UpdateDataResponse(sensingData.getId());
    }
}
