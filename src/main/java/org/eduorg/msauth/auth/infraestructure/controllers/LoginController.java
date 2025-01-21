package org.eduorg.msauth.auth.infraestructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {
    public String login() {
        return "Login";
    }
}
