package ott.ott_project.domain;

import lombok.*;
import javax.persistence.*;

//OTT 기준정보(OTT_INFO) DB 테이블
@Entity
@Table(name="ott_info")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Ottinfo {
    @Id
    @Column(name="ott_id_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 생성 위임
    private int ottIdKey;

    @Column(name="ott_var", nullable = false, length = 30)
    private String ottVar;

    @Column(name="owner", nullable = false, length = 30)
    private String owner;

    @Column(name="max_num", nullable = false)
    private int maxNum;

    @Column(name="now_num", nullable = false)
    private int nowNum;

    @Column(name="duration", nullable = false)
    private int duration;

    @Column(name="totalcost", nullable = false)
    private int totalCost;
}
