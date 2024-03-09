package gng4120.group3.project.controllers.api;

import gng4120.group3.project.models.user.ERole;
import gng4120.group3.project.models.user.Role;
import gng4120.group3.project.models.user.User;
import gng4120.group3.project.models.user.UserRef;
import gng4120.group3.project.payload.request.SigninRequest;
import gng4120.group3.project.payload.request.SignupRequest;
import gng4120.group3.project.database.repository.account.RoleRepository;
import gng4120.group3.project.database.repository.account.UserRepository;
import gng4120.group3.project.security.jwt.JwtUtils;
import gng4120.group3.project.security.services.UserDetailsImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class AuthAPIController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping(value = "/signin")
    public String authenticateUser(@ModelAttribute("signinRequest") SigninRequest signinRequest,
                                   HttpServletResponse response, HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {

        Optional<User> userOptional;

        if(userRepository.existsByEmail(signinRequest.getEmailUser())){
            userOptional = userRepository.findByEmail(signinRequest.getEmailUser());
        }
        else if(userRepository.existsByUsername(signinRequest.getEmailUser())){
            userOptional = userRepository.findByUsername(signinRequest.getEmailUser());
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "Incorrect Password or Username/Email!");
            redirectAttributes.addAttribute("isNew", false);
            return "redirect:/account/auth?error"; // Return the name of the view for the registration page
        }

        // Shouldn't necessarily be called unless object isn't of same type.
        if (userOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Incorrect Password or Username/Email!");
            redirectAttributes.addAttribute("isNew", false);
            return "redirect:/account/auth?error"; // Return the name of the view for the registration page
        }

        User user = userOptional.get();

        // Check password
        if (!encoder.matches(signinRequest.getPassword(), user.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Incorrect Password or Username/Email!");
            redirectAttributes.addAttribute("isNew", false);
            return "redirect:/account/auth?error"; // Return the name of the view for the registration page
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), signinRequest.getPassword()));

        HttpSession session = request.getSession();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        session.setAttribute("currentUser", new UserRef(user));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Add JWT cookie to the response
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

        // Redirect to the home page (/)
        return "redirect:/";
    }

    @GetMapping(value = "/signout")
    public String unauthenticateUser(RedirectAttributes redirectAttributes,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        clearUser(request, response);
        redirectAttributes.addFlashAttribute("signoutMessage", "You've Signed Out Successfully!");
        return "redirect:/";
    }

    @GetMapping(value = "/invalidate")
    public String unauthenticateUser(HttpServletRequest request,
                                     HttpServletResponse response) {
        clearUser(request, response);
        return "redirect:/";
    }

    private void clearUser(HttpServletRequest request,
                          HttpServletResponse response){
        // Invalidate the user's session
        request.getSession().invalidate();
        // Remove any authentication-related data
        SecurityContextHolder.clearContext();
        // Remove JWT cookie
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }


    @PostMapping(value = "/signup")
    public String registerUser(@ModelAttribute("signupRequest") SignupRequest signupRequest, RedirectAttributes redirectAttributes) {

        if(!signupRequest.getPassword().equals(signupRequest.getPasswordverify())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Passwords do not match!");
            redirectAttributes.addAttribute("isNew", true);
            return "redirect:/account/auth?error"; // Return the name of the view for the registration page
        }

        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email is already in use!");
            redirectAttributes.addAttribute("isNew", true);
            return "redirect:/account/auth?error"; // Return the name of the view for the registration page
        }

        signupRequest.setPassword(encoder.encode(signupRequest.getPassword()));

        // Create new user's account
        User user = new User(signupRequest);

        Set<String> strRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole;
            try {
                ERole defaultRole = ERole.ROLE_USER;
                userRole = roleRepository.findByName(defaultRole)
                        .orElseThrow(() -> new RuntimeException("Role " + defaultRole + " not found."));
                roles.add(userRole);
            } catch (RuntimeException e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Server Error: " + e.getMessage());
                e.printStackTrace();
                redirectAttributes.addAttribute("isNew", true);
                return "redirect:/account/auth?error"; // Return the name of the view for the registration page
            }
            roles.add(userRole);
        } else {
            try {
                strRoles.forEach(role -> {
                    ERole eRole = ERole.valueOf(role.toUpperCase());
                    Role existingRole = roleRepository.findByName(eRole)
                            .orElseThrow(() -> new RuntimeException("Role " + eRole + " not found."));
                    roles.add(existingRole);
                });
            } catch (RuntimeException e) {
                redirectAttributes.addFlashAttribute("errorMessage", "Server Error: " + e.getMessage());
                e.printStackTrace();
                redirectAttributes.addAttribute("isNew", true);
                return "redirect:/account/auth?error"; // Return the name of the view for the registration page
            }
        }

        user.setRoles(roles);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("successMessage", "Account Registered Successfully! Please Sign In.");
        redirectAttributes.addAttribute("isNew", false);
        return "redirect:/account/auth?success";
    }
}