package ott.ott_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Mapper;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.MapperRepository;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.repository.OttinfoRepository;
import java.util.ArrayList;
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
        System.out.println("ottApply "+memberIdKey+" "+ottIdKey);
        if(ottinfo.getNowNum()== ottinfo.getMaxNum()) {
            //인원 다참
            return -1;
        }
        mapper.setOttInfo(ottIdKey);
        mapper.setMember(memberIdKey);
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
        Member member = new Member();
        Ottinfo ottinfo = new Ottinfo();
        if(ottIdKey!=-1) {
            ottinfo = ottInfoRepository.findById(ottIdKey).orElseThrow();
            System.out.println("ottidkey " + ottinfo.getOttIdKey());
            System.out.println(ottinfo.getOwner());
            member = memberRepository.findByName(ottinfo.getOwner()).orElseThrow();
        }

        if(ottIdKey==-1) // [회원탈퇴 시 작업/검증필요]
        {
            if(memberIdKey==member.getMemIdKey()) // 현재 OTT 취소하려는 멤버와 개설자가 같으면
            {
                //개설자가 모집 삭제 시 해당 모집 신청자들의 신청도 같이 전부 취소
                System.out.println("ottkey "+ ottIdKey);
                List<Mapper> mappers = mapperRepository.findAllByMember(memberIdKey);
                List<Ottinfo> ottinfos = ottInfoRepository.findAllByOwner(member.getName());
                for (Mapper mapper1 : mappers) {
                    System.out.println("Mapper ID: " + mapper1.getMapNum());
                    // Do something with the individual Mapper object
                }
                ottInfoRepository.deleteAll(ottinfos);
                mapperRepository.deleteAll(mappers);
                return 0;
            }
            List<Mapper> mappers = mapperRepository.findAllByMember(memberIdKey);
            List<Ottinfo> ottinfos = new ArrayList<>();

            for(Mapper mapperTmp:mappers) {
                Ottinfo ottinfoTmp = ottInfoRepository.findById(mapperTmp.getOttInfo()).orElseThrow();
                ottinfos.add(ottinfoTmp);
            }
            for(Ottinfo ottinfoTmp: ottinfos)
            {
                ottinfoTmp.setNowNum(ottinfoTmp.getNowNum()-1);
            }
            mapperRepository.deleteAll(mappers);
            ottInfoRepository.saveAll(ottinfos);
            return 0;
        }

        if(memberIdKey==member.getMemIdKey()) // 현재 OTT 취소하려는 멤버와 개설자가 같으면
        //if(loginRepository.findByUserid(member.getUserid())!=null)
        {
            //개설자가 모집 삭제 시 해당 모집 신청자들의 신청도 같이 전부 취소
            System.out.println("ottkey "+ ottIdKey);
            List<Mapper> mappers = mapperRepository.findByOttInfo(ottIdKey);
            System.out.println(ottIdKey);
            System.out.println(mappers);
            for (Mapper mapper1 : mappers) {
                System.out.println("Mapper ID: " + mapper1.getMapNum());
                //디버그용 프린트문
            }
            ottInfoRepository.deleteById(ottIdKey);
            mapperRepository.deleteAll(mappers);
            return 0;
        }
        if(ottinfo.getNowNum()==1)
        {
            //현재 내 정보와 개설자 정보가 같아야 삭제 가능
            if(memberIdKey==member.getMemIdKey())
            {
                Mapper mapperTmp = mapperRepository.findByMemberAndOttInfo(memberIdKey,ottIdKey);
                mapperRepository.deleteById(mapperTmp.getMapNum());
                ottInfoRepository.deleteById(ottIdKey);
            } else {
                return -1;
            }
            //OTT_INFO 테이블의 해당 row 지워야함
            //Mapper 테이블 해당 row 삭제
            return 0;
        }
        ottinfo.setNowNum(ottinfo.getNowNum()-1);
        ottInfoRepository.save(ottinfo);
        //Mapper 테이블 해당 row 삭제
        Mapper mapperTmp = mapperRepository.findByMemberAndOttInfo(memberIdKey,ottIdKey);
        System.out.println("mappertmp: "+mapperTmp);
        mapperRepository.deleteById(mapperTmp.getMapNum());
        return 0;
    }

    //GET용
    public List<Ottinfo> showOttinfo(){
        List<Ottinfo> ottinfos = ottInfoRepository.findAll();
        return ottinfos;
    }
    public List<Ottinfo> showOttinfoByMember(int memberIdKey){
        List<Mapper> mappers = mapperRepository.findAllByMember(memberIdKey);
        List<Ottinfo> ottinfos = new ArrayList<>();
        for(Mapper mapper:mappers) {
            Ottinfo ottinfo = ottInfoRepository.findById(mapper.getOttInfo()).orElseThrow();
            ottinfos.add(ottinfo);
        }
        return ottinfos;
    }
}
