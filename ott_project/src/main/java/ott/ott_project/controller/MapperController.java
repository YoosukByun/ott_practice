package ott.ott_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ott.ott_project.domain.Ottinfo;
import ott.ott_project.service.MapperService;

import java.util.List;



@Controller
@RequiredArgsConstructor
public class MapperController {

    @Autowired
    public MapperService mapperService;

    @GetMapping("/ott/ottinfo")
    public String Ottinfoview(Model model){
        List<Ottinfo> ottInfos = mapperService.showOttinfo();
        model.addAttribute("ottinfos", ottInfos);
        return "ott/ottinfo";
    }
}
