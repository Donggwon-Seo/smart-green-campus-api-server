package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence;

import jakarta.persistence.*;
import kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence.SensingData;
import lombok.AccessLevel;
import lombok.Builder;
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

    private String email;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private final List<SensingData> sensingDataList = new ArrayList<>();

    /* 정적 생성 메서드 */
    @Builder
    public static Member createMember(final String name, final String password, final String email) {

        Member member = new Member();
        member.name = name;
        member.password = password;
        member.email = email;

        return member;
    }

    /**
     * 비즈니스 로직
     * @param name
     */
    public void update(final String name) {
        this.name = name;
    }
}
