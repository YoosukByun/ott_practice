package ott.ott_project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.ott_project.domain.Login;
import ott.ott_project.domain.Member;
import ott.ott_project.repository.LoginRepository;
import ott.ott_project.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final LoginRepository loginRepository;

    public Member login(String loginId, String password) {
        Member member = new Member();
        member = memberRepository.findByUserid(loginId);
        System.out.println(member.getUserid());
        if(loginId.equals(member.getUserid())) {
            if(password.equals(member.getPw()))
            {
                return member;
            } else {
                return null;
            }
        } else {
            return null; // 여기는 로그인 실패에 대한 익셉션 처리가 필요함
        }
    }
    public Login loginMember(String loginId){
        Login login = new Login();
        loginRepository.deleteAll(); //로그인ID 정보 저장된 부분 재로그인 시 초기화
        login.setUserid(loginId);
        loginRepository.save(login); //로그인ID 정보 저장
        return login;
    }
    public Login logoutMember(String loginId){
        Login login = new Login();
        loginRepository.deleteAll(); //로그인ID 정보 저장해제
        return login;
    }
    //@return null 로그인 실패
}
