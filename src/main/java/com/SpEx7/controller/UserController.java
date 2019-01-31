package com.SpEx7.controller;

import com.SpEx7.entity.PortalUser;
import com.SpEx7.service.UserService;
import com.SpEx7.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public void setNewsService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/showLogin")
    public String showLogin() {
        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/showReg")
    public String showReg(Model model) {
        model.addAttribute("user", new PortalUser());
        return "reg";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/reg")
    public String signOut(@ModelAttribute("user") PortalUser user){
        System.out.println(user.toString() + " cont");
        userServiceImpl.registration(user);
        return "redirect: /showLogin";
    }

    @GetMapping("/forbidden")
    public String forbidden(){
        return "forbidden";
    }
}
