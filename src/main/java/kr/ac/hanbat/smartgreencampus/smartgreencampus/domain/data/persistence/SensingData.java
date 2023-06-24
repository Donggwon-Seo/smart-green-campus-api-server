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
    private Measurement measurement;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();


    @Builder
    public SensingData(
            final Double sensingValue,
            final Measurement measurement) {
        this.sensingValue = sensingValue;
        this.measurement = measurement;
    }

    /* 비즈니스 로직 */
    public void update(final Double value) {
        this.sensingValue = value;
        this.updatedAt = LocalDateTime.now();
    }
}
