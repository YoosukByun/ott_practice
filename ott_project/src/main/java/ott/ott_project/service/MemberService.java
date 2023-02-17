package ott.ott_project.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Member;
import ott.ott_project.repository.MemberRepository;
import java.util.List;


import static java.sql.Types.NULL;


@Service
public class MemberService {
    //회원가입
    @Autowired
    public MemberRepository memberRepository;
    /*
    public int createMember(Member member) {
        memberRepository.save(member);
        return member.getMem_id_key();
    }*/
    //회원탈퇴
    public boolean deleteMember(Member member){
        int i = member.getMem_id_key();
        memberRepository.deleteById(i);
        memberRepository.save(member);

        if(member.getMem_id_key()!=NULL) {
            return false;
        }
        else {
            return true;
        }

    }

    //로그인 프로세스
    /*public boolean loginProcess(String Id, String Pw){
        Member member = new Member();
        if(memberRepository.findByName(Id) != null){
            if(memberRepository.findByPw(Pw) != null){
                return true;
            }
        } else {
            return false;
        }
        return false;
    }*/

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

    //로그아웃 프로세스 (컨트롤러에서 구현)
}
