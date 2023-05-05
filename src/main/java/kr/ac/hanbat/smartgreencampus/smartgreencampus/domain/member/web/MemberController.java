package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.web.dto.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.application.MemberService;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApi;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.annotation.swagger.SwaggerApiFailWithoutAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "회원")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @SwaggerApi(summary = "닉네임 수정", implementation = ResponseEntity.class)
    @SwaggerApiFailWithoutAuth
    @PatchMapping("/api/members/{id}")
    public ResponseEntity<UpdateMemberResponse> updateMember(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final UpdateMemberRequest request) {

        memberService.update(id, request.name());
        Member member = memberService.findById(id);

        return ResponseEntity.ok(new UpdateMemberResponse(member.getId(), member.getName()));
    }


    @SwaggerApi(summary = "회원 탈퇴", implementation = ResponseEntity.class)
    @SwaggerApiFailWithoutAuth
    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<DeleteMemberRequest> deleteMember(@PathVariable final Long id) {

        memberService.deleteMember(id);
        return ResponseEntity.ok(new DeleteMemberRequest(id));
    }
}
