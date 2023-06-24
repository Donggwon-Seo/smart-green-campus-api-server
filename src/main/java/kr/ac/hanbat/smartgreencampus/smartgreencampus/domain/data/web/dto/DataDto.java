package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataDto {
    private Double value;
    private String kind;

    public DataDto(final SensingData data) {
        this.value = data.getSensingValue();
        this.kind = String.valueOf(data.getMeasurement());
    }
}
