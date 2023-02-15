package ott.ott_project.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
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
public class Ott_info {
    @Id
    @Column(name="ott_id_key", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 생성 위임
    private int ott_id_key;

    @Column(name="ott_var", nullable = false, length = 30)
    private String ott_var;

    @Column(name="owner", nullable = false, length = 30)
    private String owner;

    @Column(name="max_num", nullable = false)
    private int max_num;

    @Column(name="now_num", nullable = false)
    private int now_num;

    @Column(name="duration", nullable = false)
    private int duration;

    @Column(name="totalcost", nullable = false)
    private int totalcost;
}
