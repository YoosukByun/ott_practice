package ott.ott_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ott.ott_project.service.OttinfoService;

@Controller
public class OttinfoController {
    private final OttinfoService ottinfoService;

    @Autowired
    public OttinfoController(OttinfoService ottinfoService) {
        this.ottinfoService = ottinfoService;
    }

    @GetMapping("/ott/new")
    public String createForm() {
        return "ott/createOttForm";
    }

    @PostMapping("/ott/new")
    public String CreateOtt(
            @RequestParam(value = "ottvar") String ott_var,
            @RequestParam(value = "owner") String owner,
            @RequestParam(value = "maxnum") int maxNum,
            @RequestParam(value = "duration") int duration,
            @RequestParam(value = "totalcost") int totalCost,
            Model model){
        model.addAttribute("ottvar", ott_var);
        model.addAttribute("owner", owner);
        model.addAttribute("maxnum", maxNum);
        model.addAttribute("duration", duration);
        model.addAttribute("totalcost", totalCost);
        int nowNum =0;
        String tmp = ottinfoService.createOttShare(ott_var, owner, maxNum, nowNum, duration, totalCost);
        if (tmp == null)
        {
            return "redirect:/ott/new";
        }
        return "redirect:/";
    }
}
