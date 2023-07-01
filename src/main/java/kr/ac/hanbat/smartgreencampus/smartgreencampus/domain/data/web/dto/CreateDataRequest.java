package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateDataRequest {

    List<Data> dataList = new ArrayList<>();

    public CreateDataRequest(List<Data> dataList) {
        this.dataList = dataList;
    }

    public record Data(Double value, String measurement) { }
}