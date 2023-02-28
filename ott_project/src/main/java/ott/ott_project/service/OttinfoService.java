package ott.ott_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.MapperRepository;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.repository.OttinfoRepository;

import java.util.List;

@Service
public class OttinfoService {

    @Autowired
    public OttinfoRepository ottinfoRepository;
    @Autowired
    public MapperRepository mapperRepository;
    @Autowired
    public MapperService mapperService;
    @Autowired
    public MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    //OTT 개설
    //멤버 이름은 로그인 후, 본인 회원정보에 해당하는 멤버키를 받아야함
    //아직 미구현이며, 로그인 기능 구현 후, owner는 Member 테이블의 '본인 회원정보.회원명'으로 교체 예정
    public String createOttShare(String ottVar, String owner, int maxNum, int nowNum, int duration, int totalCost) {
        Member member = memberRepository.findByName(owner).orElseThrow();
        if(member==null)
        {
            return null;
        }
        System.out.println(member.getMemIdKey());
        Ottinfo ottinfo = new Ottinfo();
        ottinfo.setOttVar(ottVar);
        ottinfo.setOwner(owner);
        ottinfo.setMaxNum(maxNum);
        ottinfo.setNowNum(nowNum);
        ottinfo.setDuration(duration);
        ottinfo.setTotalCost(totalCost);
        ottinfoRepository.save(ottinfo);
        System.out.println(member.getMemIdKey()+"  "+ottinfo.getOttIdKey());
        mapperService.OttApply(member.getMemIdKey(), ottinfo.getOttIdKey());
        return ottinfo.getOwner();
    }
}