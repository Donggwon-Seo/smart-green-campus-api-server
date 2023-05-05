package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.sensingdata;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataDto {
    private String name;
    private Double value;
    private String kind;
    private String building; //Location
    private String details;  //Location
    private String maker;

    public DataDto(final SensingData data) {
        this.name = data.getName();
        this.value = data.getSensingValue();
        this.kind = String.valueOf(data.getKind());
        this.building = data.getLocation().getBuilding();
        this.details = data.getLocation().getDetails();
        this.maker = data.getMember().getName();
    }
}
