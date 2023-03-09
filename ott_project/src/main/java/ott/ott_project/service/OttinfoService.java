package ott.ott_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.MapperRepository;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.repository.OttinfoRepository;

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