package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findValidateMemberByName(final String name);
    Optional<Member> findByEmail(final String email);
}
