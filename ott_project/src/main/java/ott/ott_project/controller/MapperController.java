package ott.ott_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ott.ott_project.domain.Member;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.repository.MemberRepository;
import ott.ott_project.repository.OttinfoRepository;
import ott.ott_project.service.MapperService;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MapperController {

    @Autowired
    public MapperService mapperService;
    private final MemberRepository memberRepository;
    private final OttinfoRepository ottinfoRepository;

    @GetMapping("/ott/ottinfo")
    public String Ottinfoview(Model model){
        List<Ottinfo> ottInfos = mapperService.showOttinfo();
        model.addAttribute("ottinfos", ottInfos);
        return "ott/ottinfo";
    }

    //OTT 신청 취소
    @GetMapping("ott/ottinfo/{ottIdKey}")
    public String UpdateDel(@PathVariable("ottIdKey") String ottIdKey, Model model) {
        model.addAttribute("ottinfo.ottIdKey", ottIdKey);
        System.out.println(Integer.parseInt(ottIdKey)+"ottIdKey");
        String loginId = mapperService.getLoginMember();
        Member member = memberRepository.findByUserid(loginId);
        Integer ottIdKeyTmp = Integer.parseInt(ottIdKey);
        mapperService.OttCancel(member.getMemIdKey(), ottIdKeyTmp);
        return "redirect:/ott/ottinfo";
    }

    //OTT 신청 추가
    @GetMapping("ott/ottinfo/add/{ottIdKey}")
    public String UpdateAdd(@PathVariable("ottIdKey") String ottIdKey, Model model) {
        model.addAttribute("ottinfo.ottIdKey", ottIdKey);
        System.out.println(Integer.parseInt(ottIdKey)+"ottIdKey");
        String loginId = mapperService.getLoginMember();
        Member member = memberRepository.findByUserid(loginId);
        Integer ottIdKeyTmp = Integer.parseInt(ottIdKey);
        mapperService.OttApply(member.getMemIdKey(),ottIdKeyTmp);
        return "redirect:ott/ottinfo";
    }

}
