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

    /** 회원 탈퇴 */
    @Transactional
    public void deleteMember(final Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(NullMemberException::new);
        memberRepository.delete(member);
    }
}
