package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence;

import jakarta.persistence.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SensingData {

    @Id
    @GeneratedValue
    private Long id;

    private Double sensingValue;

    @Enumerated(value = EnumType.STRING)
    private SensingKind kind;

    @Embedded
    private Location location;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    /* 연관관계 편의 메서드 */
    public void setMember(final Member member) {

        this.member = member;
        member.getSensingDataList().add(this);
    }

    @Builder
    public SensingData(
            final Long id,
            final Double sensingValue,
            final SensingKind kind,
            final Location location,
            final Member member) {
        this.id = id;
        this.sensingValue = sensingValue;
        this.kind = kind;
        this.location = location;
        this.member = member;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /* 비즈니스 로직 */
    public void update(final Double value) {
        this.sensingValue = value;
        this.updatedAt = LocalDateTime.now();
    }
}
