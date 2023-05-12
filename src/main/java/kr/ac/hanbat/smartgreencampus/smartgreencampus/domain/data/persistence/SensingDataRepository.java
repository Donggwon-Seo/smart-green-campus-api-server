package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensingDataRepository extends JpaRepository<SensingData, Long> {

    @Query("select d from SensingData d join fetch d.member m")
    List<SensingData> findAllByMember();


    @Query("select d from SensingData d join fetch d.member m where d.measurement = :measurement")
    List<SensingData> findAllByMemberKind(@Param("measurement") final Measurement measurement);
}
