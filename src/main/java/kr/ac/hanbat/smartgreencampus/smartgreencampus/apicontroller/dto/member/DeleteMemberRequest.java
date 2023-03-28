package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DeleteMemberRequest {
    private Long id;
}
