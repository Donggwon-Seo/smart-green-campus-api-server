package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;

    @OneToMany(mappedBy = "member")
    private final List<SensingData> sensingDataList = new ArrayList<>();

    /* 정적 생성 메서드 */
    public static Member createMember(final String name, final String password) {

        Member member = new Member();
        member.name = name;
        member.password = password;

        return member;
    }

    /**
     * 비즈니스 로직
     * @param name
     */
    public void update(String name) {
        this.name = name;
    }
}
