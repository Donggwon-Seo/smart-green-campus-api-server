package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence;

import jakarta.persistence.Embeddable;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.global.exception.IllegalValueException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    private String building;
    private String details;

    /* 정적 생성 메서드 */
    public static Location createLocation(final String building, final String details) {

        if (!StringUtils.hasText(building) || !StringUtils.hasText(details)) {
            log.error("createLocation() : 매개 변수가 유효하지 않습니다.");
            throw new IllegalValueException("createLocation() : 매개 변수가 유효하지 않습니다.");
        }

        Location location = new Location();
        location.building = building;
        location.details = details;

        return location;
    }
}
