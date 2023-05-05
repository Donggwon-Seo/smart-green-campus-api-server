package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.application;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.Location;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.SensingDataByKindRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.CreateDataRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.application.MemberService;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingKind;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck.NullSensingDataException;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingDataRepository;
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
    public Long save(final CreateDataRequest request) {

        final SensingKind kind = SensingKind.of(request.sensingKind());
        final Location location = Location.createLocation(request.building(), request.details());

        final Member member = memberService.findById(request.memberId());
        final SensingData sensingData = SensingData.builder()
                .member(member)
                .sensingValue(request.value())
                .kind(kind)
                .location(location)
                .build();

        sensingDataRepository.save(sensingData);
        return sensingData.getId();
    }

    /* 데이터 값 수정 */
    @Transactional
    public void update(final Long dataId, final Double value) {

        final SensingData sensingData = findById(dataId);
        sensingData.update(value);
    }

    public SensingData findById(final Long dataId) {
        return sensingDataRepository.findById(dataId).orElseThrow(NullSensingDataException::new);
    }


    public List<SensingData> findAllByMember() { return sensingDataRepository.findAllByMember(); }

    public List<SensingData> findAllByMemberKind(final SensingDataByKindRequest request) {

        final SensingKind sensingKind = SensingKind.of(request.kind());
        return sensingDataRepository.findAllByMemberKind(sensingKind);
    }
}
