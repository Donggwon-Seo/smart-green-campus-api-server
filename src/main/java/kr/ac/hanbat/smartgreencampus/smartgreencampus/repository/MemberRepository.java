package kr.ac.hanbat.smartgreencampus.smartgreencampus.repository;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
