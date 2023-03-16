package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller;

import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /* 회원 조회 */
    @GetMapping("/api/members")
    public Result members() {

        List<Member> members = memberService.findAll();

        List<MemberDto> memberDtos = members.stream()
                .map(member -> new MemberDto(member.getName()))
                .collect(Collectors.toList());

        return new Result(memberDtos);
    }

    /* 회원 등록 */
    @PostMapping("/api/members")
    public CreateMemberResponse saveMember(
            @RequestBody @Valid CreateMemberRequest request) {

        Long id = memberService.save(request.name, request.password);
        return new CreateMemberResponse(id);
    }

    /* 회원 수정 */
    @PatchMapping("/api/members/{id}")
    public UpdateMemberResponse updateMember(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.name);
        Member member = memberService.findById(id);

        return new UpdateMemberResponse(member.getId(), member.getName());
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T contents;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberRequest {
        private String name;
        private String password;
    }

    @Data
    @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }
}
