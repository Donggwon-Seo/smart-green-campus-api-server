package kr.ac.hanbat.smartgreencampus.smartgreencampus.global.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    boolean existsByToken(final String token);
}
