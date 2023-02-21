package ott.ott_project.domain;

import lombok.*;

import javax.persistence.*;
@Entity
@Table(name="login")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Login {
    @Id
    @Column(name="login_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 생성 위임
    private int loginMember;
    @Column(name="login_member", nullable = false, length = 30)
    private String userid;
}
