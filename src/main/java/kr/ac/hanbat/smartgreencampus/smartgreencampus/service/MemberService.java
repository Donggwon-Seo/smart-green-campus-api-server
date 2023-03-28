package kr.ac.hanbat.smartgreencampus.smartgreencampus.service;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /*
     * 회원 저장
     */
    @Transactional
    public Long save(final String name, final String password) {

        Member member = Member.createMember(name, password);
        memberRepository.save(member);
        return member.getId();
    }

    /*
     * 회원 이름 수정
     */
    @Transactional
    public void update(final Long memberId, final String name) {

        Member member = memberRepository.findById(memberId);
        member.update(name);
    }

    /* 회원 탈퇴 */
    @Transactional
    public void deleteMember(final Long id) {
        memberRepository.deleteMember(id);
    }

    public Member findById(final Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
