package ott.ott_project.domain;

import lombok.*;

import javax.persistence.*;

//맵핑(MAPPER) DB 테이블
@Entity
@Table(name="mapper")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Mapper {
    @Id
    @Column(name="map_num", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에 생성 위임
    private int map_num;
    @ManyToOne
    @JoinColumn(name="member_id_key")
    private Member member;
    @ManyToOne
    @JoinColumn(name="ott_id_key")
    private Ott_info ott_info;
}
