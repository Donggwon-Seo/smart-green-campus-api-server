package kr.ac.hanbat.smartgreencampus.smartgreencampus.service;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Location;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.SensingKind;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.repository.SensingDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensingDataService {

    private final SensingDataRepository sensingDataRepository;
    private final MemberService memberService;

    /*
     * 데이터 저장
     */
    @Transactional
    public Long save(
            final Long memberId,
            final String name,
            final Double value,
            final SensingKind kind,
            final Location location) {

        Member member = memberService.findById(memberId);
        SensingData sensingData = SensingData.createData(member, name, value, kind,location);

        sensingDataRepository.save(sensingData);
        return sensingData.getId();
    }

    /* 데이터 값 수정 */
    @Transactional
    public void update(final Long dataId, final Double value) {

        SensingData sensingData = sensingDataRepository.findById(dataId);
        sensingData.update(value);
    }

    public SensingData findById(Long dataId) {
        return sensingDataRepository.findById(dataId);
    }

    public List<SensingData> findAll() {
        return sensingDataRepository.findAll();
    }

    public List<SensingData> findAllByMember() {
        return sensingDataRepository.findAllByMembeer();
    }
}
