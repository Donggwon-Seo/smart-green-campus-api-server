package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    private String building;
    private String details;

    /* 정적 생성 메서드 */
    public static Location createLocation(final String building, final String details) {

        Location location = new Location();
        location.building = building;
        location.details = details;

        return location;
    }
}
