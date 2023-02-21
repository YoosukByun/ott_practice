package ott.ott_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.service.MapperService;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class MapperController {

    @Autowired
    public MapperService mapperService;
    private final MemberRepository memberRepository;

    @GetMapping("/ott/ottinfo")
    public String Ottinfoview(Model model){
        List<Ottinfo> ottInfos = mapperService.showOttinfo();
        model.addAttribute("ottinfos", ottInfos);
        return "ott/ottinfo";
    }

    //OTT 신청 취소
    @GetMapping("ott/ottinfo/del/{ottinfo.ottIdKey}")
    public String UpdateDel(@RequestParam(value="ottinfo.ottIdKey") int ottIdKey, Model model) {
        model.addAttribute("ottinfo.ottIdkey", ottIdKey);

        String loginId = mapperService.getLoginMember();
        Member member = memberRepository.findByUserid(loginId);
        mapperService.OttCancel(member.getMemIdKey(),ottIdKey);
        return "ott/ottinfo";
    }

    //OTT 신청 추가
    @PostMapping("ott/ottinfo/add")
    public String UpdateAdd(@RequestParam("ottinfo.ottIdKey") int ottIdKey, Model model) {
        model.addAttribute("ottinfo.ottIdkey", ottIdKey);
        String loginId = mapperService.getLoginMember();
        Member member = memberRepository.findByUserid(loginId);
        mapperService.OttApply(member.getMemIdKey(),ottIdKey);
        return "ott/ottinfo";
    }

}
