package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.application;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.auth.web.dto.SignupRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck.NullMemberException;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /** 회원 이름 수정 */
    @Transactional
    public void update(final Long memberId, final String name) {

        Member member = findById(memberId);
        member.update(name);
    }

    /** 회원 탈퇴 */
    @Transactional
    public void deleteMember(final Long id) {

        Member member = findById(id);
        memberRepository.delete(member);
    }

    public Member findById(final Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(NullMemberException::new);
    }
}
