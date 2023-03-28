package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberRequest {
    private String name;
    private String password;
}
