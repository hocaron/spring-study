package Study.SpringStudy.controller.study;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {

    @GetMapping("study")
    public String study(Model model){
        model.addAttribute("data", "Study now!");
        return "study";
    }


}
