package com.rzjaffery.hms.controller;

import com.rzjaffery.hms.dto.LoginRequest;
import com.rzjaffery.hms.dto.RegisterRequest;
import com.rzjaffery.hms.entity.User;
import com.rzjaffery.hms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request){
        User user = new User ();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setTermAccepted (request.isTermsAccepted ());
        return userService.registerUser (user,request.getConfirmPassword ());
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request){
        return userService.login (request.getEmail (), request.getPassword ());
    }
}
