package org.eduorg.msauth.auth.infraestructure.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/register")
public class RegisterController {
    public String register() {
        return "Register";
    }
}
