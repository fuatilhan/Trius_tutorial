package trius.springframework.controllers;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import trius.springframework.repositories.UserRepository;
import trius.springframework.security.UserAccount;

import javax.validation.Valid;

@Controller
public class HomeController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "login";
    }

    @GetMapping("/register")
    public String adduser(){
        return "register";
    }

    @PostMapping("register")
    public String registerUser(@Valid UserAccount user) {
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        userRepository.save(new UserAccount(user.getEmail(), passwordEncoder.encode(user.getPassword())));
        return "redirect:/login/";

    }

}
