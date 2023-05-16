package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.application;

import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.SensingDataByKindRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.web.dto.CreateDataRequest;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.application.MemberService;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence.Member;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.Measurement;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.nullcheck.NullSensingDataException;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

        final Measurement measurement = Measurement.of(request.measurement());

        final Member member = memberService.findById(request.memberId());
        final SensingData sensingData = SensingData.builder()
                .member(member)
                .sensingValue(request.value())
                .measurement(measurement)
                .build();

        sensingDataRepository.save(sensingData);
        log.info(member.getName() + "님이 새로운 센싱 값을 저장했습니다." +
                "\nmeasurement : " + sensingData.getMeasurement() +
                "\nsensingValue: " + sensingData.getSensingValue());

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

        final Measurement measurement = Measurement.of(request.kind());
        return sensingDataRepository.findAllByMemberKind(measurement);
    }
}
