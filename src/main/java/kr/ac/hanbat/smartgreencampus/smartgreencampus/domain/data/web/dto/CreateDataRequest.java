package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.sensingdata;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateDataRequest {
    private Long memberId;
    private String name;
    private Double value;
    private String sensingKind;
    private String building; //Location
    private String details;  //Location
}
