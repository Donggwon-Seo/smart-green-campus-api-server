package kr.ac.hanbat.smartgreencampus.smartgreencampus.repository;

import jakarta.persistence.EntityManager;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SensingDataRepository {

    private final EntityManager em;

    /*
     * 데이터 생성
     */
    public void save(final SensingData sensingData) {
        em.persist(sensingData);
    }

    /*
     * 데이터 단건 조회
     */
    public SensingData findById(final Long dataId) {
        return em.find(SensingData.class, dataId);
    }

    public List<SensingData> findAll() {
        return em.createQuery("select d from SensingData d", SensingData.class)
                .getResultList();
    }

    public List<SensingData> findAllByMembeer() {
        return em.createQuery("select d from SensingData d join fetch d.member m", SensingData.class)
                .getResultList();
    }
}
