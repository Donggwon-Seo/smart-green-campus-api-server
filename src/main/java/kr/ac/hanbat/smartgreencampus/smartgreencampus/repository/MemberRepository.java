package kr.ac.hanbat.smartgreencampus.smartgreencampus.repository;

import jakarta.persistence.EntityManager;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    /* 회원 저장 */
    public void save(final Member member) {
        em.persist(member);
    }


    public void deleteMember(final Long id) {
        Member member = findById(id);
        em.remove(member);
    }

    /* 회원 단건 조회 */
    public Member findById(final Long memberId) {

        return em.find(Member.class, memberId);
    }

    /* 회원 목록 조회 */
    public List<Member> findAll() {

        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
