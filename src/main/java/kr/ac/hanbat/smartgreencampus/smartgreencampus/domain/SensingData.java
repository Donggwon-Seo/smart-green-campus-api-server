package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SensingData {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private Double sensingValue;

    @Enumerated(value = EnumType.STRING)
    private SensingKind kind;

    @Embedded
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /* 연관관계 편의 메서드 */
    public void setMember(final Member member) {

        this.member = member;
        member.getSensingDataList().add(this);
    }

    /* 정적 생성 메서드 */
    public static SensingData createData(
            final Member member,
            final String name,
            final Double value,
            final SensingKind kind,
            final Location location) {

        SensingData sensingData = new SensingData();
        sensingData.setMember(member);
        sensingData.name = name;
        sensingData.sensingValue = value;
        sensingData.kind = kind;
        sensingData.location = location;

        return sensingData;
    }

    /* 비즈니스 로직 */
    public void update(final Double value) {
        this.sensingValue = value;
    }
}
