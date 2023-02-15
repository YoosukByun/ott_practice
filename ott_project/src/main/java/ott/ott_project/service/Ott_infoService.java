package ott.ott_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Ott_info;
import ott.ott_project.repository.Ott_infoRepository;

@Service
public class Ott_infoService {

    @Autowired
    public Ott_infoRepository ott_infoRepository;

    //OTT 개설
    //멤버 이름은 로그인 후, 본인 회원정보에 해당하는 멤버키를 받아야함
    //아직 미구현이며, 로그인 기능 구현 후, owner는 Member 테이블의 '본인 회원정보.회원명'으로 교체 예정
    public String createOttShare(String ott_var, String owner, int maxNum, int nowNum, int duration, int totalCost) {
        Ott_info ott_Info = new Ott_info();
        ott_Info.setOtt_var(ott_var);
        ott_Info.setOwner(owner);
        ott_Info.setMax_num(maxNum);
        ott_Info.setNow_num(nowNum);
        ott_Info.setDuration(duration);
        ott_Info.setTotalcost(totalCost);
        ott_infoRepository.save(ott_Info);
        return ott_Info.getOwner();
    }
}