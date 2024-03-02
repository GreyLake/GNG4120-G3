package gng4120.group3.project.infrastructure.controllers.account;

import gng4120.group3.project.payload.request.SigninRequest;
import gng4120.group3.project.payload.request.SignupRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AuthController {

    @RequestMapping("/auth")
    public String authPage(@RequestParam(required = false, name = "isNew") Boolean isNew, Model model) {

        model.addAttribute("isNew", isNew != null && isNew);

        model.addAttribute("signupRequest", new SignupRequest());

        model.addAttribute("signinRequest", new SigninRequest());

        return "pages/account/auth"; // Assuming authPage.html is your authentication page
    }
}