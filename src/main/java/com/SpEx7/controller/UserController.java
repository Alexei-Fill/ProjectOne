package com.SpEx7.controller;

import com.SpEx7.entity.User;
import com.SpEx7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("user")
public class UserController {

    private UserService userService;

    @Autowired
    @Qualifier("userService")
    public void setNewsService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signIn")
    public String authorization(@ModelAttribute("user") User user){
        if(userService.authorization(user)){
            return "redirect: /newsList";
        } else {
            return "";
        }
    }

    @PostMapping("/signOut")
    public String signOut(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "";
    }
}
