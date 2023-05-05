package kr.ac.hanbat.smartgreencampus.smartgreencampus.repository;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensingDataRepository extends JpaRepository<SensingData, Long> {

    @Query("select d from SensingData d join fetch d.member m")
    public List<SensingData> findAllByMember();
}
