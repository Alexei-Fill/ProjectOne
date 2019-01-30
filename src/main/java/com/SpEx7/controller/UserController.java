package com.SpEx7.controller;

import com.SpEx7.service.UserService;
import com.SpEx7.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    public void setNewsService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

//    @PostMapping("/login")
//    public String authorization(@ModelAttribute("user") PortalUser user) {
//        if (userService.authorization(user)) {
//            return "redirect: /newsList";
//        } else {
//            return "login";
//        }
//    }

    @GetMapping("/showLogin")
    public String showLogin() {
        return "login";
    }


//
//    @PostMapping("/signOut")
//    public String signOut(SessionStatus sessionStatus){
//        sessionStatus.setComplete();
//        return "login";
//    }
}
