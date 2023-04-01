package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.sensingdata;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateDataRequest {
    private Long dataId;
    private Double value;
}
