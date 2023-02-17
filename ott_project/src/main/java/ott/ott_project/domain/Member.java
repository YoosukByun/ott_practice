package ott.ott_project.domain;

import lombok.*;

import javax.persistence.*;

//멤버(MEMBER) DB 테이블
@Entity
@Table(name="member")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Member {
    @Id
    @Column(name="member_id_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 생성 위임
    private int mem_id_key;

    @Column(name="userid", nullable = false, length = 30)
    private String userid;

    @Column(name="pw", nullable = false, length = 30)
    private String pw;

    @Column(name="name", nullable = false, length = 40)
    private String name;

    @Column(name="phone_num", nullable = false)
    private int phoneNum;

    @Column(name="account", nullable = false, length = 50)
    private String account;

}
