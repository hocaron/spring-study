package Study.SpringStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class StudyController {
    @GetMapping("study")
    public String study(Model model){
        model.addAttribute("data", "Study now!");
        return "study";
    }
}

