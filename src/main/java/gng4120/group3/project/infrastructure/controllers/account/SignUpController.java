package gng4120.group3.project.infrastructure.controllers.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class SignUpController {

    @RequestMapping("/signup")
    public String signUp(){
        return "pages/account/auth";
    }

}
