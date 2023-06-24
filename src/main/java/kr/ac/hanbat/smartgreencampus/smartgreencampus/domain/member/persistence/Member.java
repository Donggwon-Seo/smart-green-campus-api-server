package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.member.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;


    /* 정적 생성 메서드 */
    @Builder
    public static Member createMember(final String password, final String email) {

        Member member = new Member();
        member.password = password;
        member.email = email;

        return member;
    }
}
