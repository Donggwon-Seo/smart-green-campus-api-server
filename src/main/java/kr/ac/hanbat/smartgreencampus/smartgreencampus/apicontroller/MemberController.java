package kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller;

import jakarta.validation.Valid;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.apicontroller.dto.member.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.service.MemberService;
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
            @RequestBody @Valid final CreateMemberRequest request) {

        Long id = memberService.save(request.getName(), request.getPassword());
        return new CreateMemberResponse(id);
    }

    /* 회원 수정 */
    @PatchMapping("/api/members/{id}")
    public UpdateMemberResponse updateMember(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Member member = memberService.findById(id);

        return new UpdateMemberResponse(member.getId(), member.getName());
    }

    /* 회원 삭제 */
    @DeleteMapping("/api/members/{id}")
    public DeleteMemberRequest deleteMember(@PathVariable final Long id) {

        memberService.deleteMember(id);
        return new DeleteMemberRequest(id);
    }
}
