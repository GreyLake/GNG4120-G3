package gng4120.group3.project.infrastructure.controllers.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class SignInController {

    @RequestMapping({"/login", "/signin"})
    public String signIn(){
        return "pages/signin";
    }
}
