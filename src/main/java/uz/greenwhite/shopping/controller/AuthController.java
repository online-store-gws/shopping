package uz.greenwhite.shopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.greenwhite.shopping.dto.LoginDto;

@Controller
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    Logger log = LoggerFactory.getLogger(AuthController.class.getName());


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginData", new LoginDto());
        return "login";
    }
//
//    @PostMapping("/auth")
//    public String login(@ModelAttribute("loginData") LoginDto loginData) {
//        log.info("keldik ................................." + loginData.getUsername());
//        System.out.println("----------------------------------------------------");
//        try {
//            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(auth);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "admin/dashboard";
//    }
}
