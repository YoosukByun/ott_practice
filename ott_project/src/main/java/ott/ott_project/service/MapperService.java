package ott.ott_project.service;

import org.hibernate.event.spi.AbstractEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.AttributeAccessor;
import org.springframework.stereotype.Service;
import ott.ott_project.controller.LoginController;
import ott.ott_project.domain.Mapper;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.MapperRepository;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.repository.OttinfoRepository;

import java.util.List;

@Service
public class MapperService {

    @Autowired
    public MapperRepository mapperRepository;
    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public OttinfoRepository ottInfoRepository;

    //신청 시 맵핑 테이블 업데이트 및 OTT
    //SET 용
    public int OttApply(int memberIdKey, int ottIdKey)
    {
        Mapper mapper = new Mapper();
        Ottinfo ottinfo = new Ottinfo();
        ottinfo = ottInfoRepository.findById(ottIdKey).orElseThrow();
        if(ottinfo.getNowNum()== ottinfo.getMaxNum()) {
            //인원 다참
            return -1;
        }
        mapper.setOttInfo(ottInfoRepository.findById(ottIdKey).orElseThrow());
        mapper.setMember(memberRepository.findById(memberIdKey).orElseThrow());
        //JPA response(findById 등등)가 optional이라 optional value 가져올 땐
        //이에 상응하는 get()/orElse()/orElseGet()/orElseThrow() 등의 메소드 써야함.
        mapperRepository.save(mapper);
        ottinfo.setNowNum(ottinfo.getNowNum()+1);
        ottInfoRepository.save(ottinfo);
        return mapper.getMapNum();
    }

    public int OttCancel(int memberIdKey, int ottIdKey)
    {
        Mapper mapper = new Mapper();
        Ottinfo ottinfo = new Ottinfo();
        ottinfo = ottInfoRepository.findById(ottIdKey).orElseThrow();
        if(ottinfo.getNowNum()==1)
        {
            //현재 내 정보와 개설자 정보가 같아야 삭제 가능
            //OTT_INFO 테이블의 해당 row 지워야함
            //Mapper 테이블 해당 row 삭제
        }
        ottinfo.setNowNum(ottinfo.getNowNum()-1);
        //Mapper 테이블 해당 row 삭제
        return 0;
    }

    //GET용
    public List<Ottinfo> showOttinfo(){
        List<Ottinfo> ottinfos = ottInfoRepository.findAll();
        return ottinfos;
    }
}
