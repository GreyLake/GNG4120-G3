package gng4120.group3.project.infrastructure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LearnController {
    @RequestMapping("/learn")
    public String home(){
        return "pages/learn";
    }
}
