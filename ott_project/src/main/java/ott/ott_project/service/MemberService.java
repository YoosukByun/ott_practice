package ott.ott_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Member;
import ott.ott_project.repository.MapperRepository;
import ott.ott_project.repository.MemberRepository;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    public MemberRepository memberRepository;
    public MapperRepository mapperRepository;

    //회원 가입
    public String memberRegister(String userId, String userPw, String Name, int phoneNum, String Account)
    {
        Member member = new Member();
        member.setUserid(userId);
        member.setName(Name);
        member.setPw(userPw);
        member.setPhoneNum(phoneNum);
        member.setAccount(Account);
        memberRepository.save(member);
        return member.getUserid();
    }
    //전체 멤버 조회
    public List<Member> findMembers() {return memberRepository.findAll();}

    //회원 탈퇴
    public int memberQuit(int memIdKey)
    {
        System.out.println("현재 로그인한 회원 데이터 삭제");
        memberRepository.deleteById(memIdKey);

        return 0;
    }
}
