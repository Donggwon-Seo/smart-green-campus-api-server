package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMemberRequest {
    private String name;
}
