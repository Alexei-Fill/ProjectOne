package com.SpEx7.controller;

import com.SpEx7.entity.User;
import com.SpEx7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public void setNewsService(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/login")
//    public String authorization(@ModelAttribute("user") User user) {
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
