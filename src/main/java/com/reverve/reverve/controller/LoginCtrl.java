package com.reverve.reverve.controller;

import com.reverve.reverve.domain.Login;
import com.reverve.reverve.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginCtrl {

    private final LoginService loginService;

    @Autowired  // Make sure Spring injects LoginService
    public LoginCtrl(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String processlogin(String username, String password, Model model) {
        Login user = loginService.login(username, password);
        if (user != null) {
            return "redirect:/welcome";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}