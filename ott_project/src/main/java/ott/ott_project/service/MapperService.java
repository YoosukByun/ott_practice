package ott.ott_project.service;

import org.hibernate.event.spi.AbstractEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.AttributeAccessor;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Login;
import ott.ott_project.domain.Mapper;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.LoginRepository;
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
    @Autowired
    private LoginRepository loginRepository;

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
        if(loginRepository.findByUserid(ottinfo.getOwner())!=null)
        {
            //개설자가 모집 삭제 시 해당 모집 신청자들의 신청도 같이 전부 취소
            ottInfoRepository.deleteById(ottIdKey);
            mapperRepository.deleteByOttInfo(ottIdKey);
        }
        if(ottinfo.getNowNum()==1)
        {
            //현재 내 정보와 개설자 정보가 같아야 삭제 가능
            if(loginRepository.findByUserid(ottinfo.getOwner())!=null)
            {
                mapperRepository.deleteByMemberAndOttInfo(memberIdKey,ottIdKey);
                ottInfoRepository.deleteById(ottIdKey);
            } else {
                return -1;
            }
            //OTT_INFO 테이블의 해당 row 지워야함
            //Mapper 테이블 해당 row 삭제
        }
        ottinfo.setNowNum(ottinfo.getNowNum()-1);
        //Mapper 테이블 해당 row 삭제
        mapperRepository.deleteByMemberAndOttInfo(memberIdKey,ottIdKey);
        return 0;
    }

    //GET용
    public List<Ottinfo> showOttinfo(){
        List<Ottinfo> ottinfos = ottInfoRepository.findAll();
        return ottinfos;
    }

    public String getLoginMember()
    {
        List<Login> login = loginRepository.findAll();
        for(int i=0; i<100; i++) {

            if(login.get(i).getLoginMember()!=0)
            {
                return login.get(i).getUserid();
            }

        }
        return null;
    }
}
