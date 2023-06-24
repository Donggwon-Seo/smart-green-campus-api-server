package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SensingDataRepository extends JpaRepository<SensingData, Long> {
}
