package com.codeup.blog.blog.controllers;

import com.codeup.blog.blog.models.User;
import com.codeup.blog.blog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model, @ModelAttribute String missing, @ModelAttribute String takenUsername ){
        model.addAttribute("user", new User());

        if (takenUsername != null){
            model.addAttribute("takenUsername", takenUsername);
        }

        if (missing != null){
            model.addAttribute("missing", missing);
        }
        return "users/sign-up";
    }

    @PostMapping("/users/sign-up")
    public String saveUser(@ModelAttribute User user, Model vModel){
        if (userDao.findByUsername(user.getUsername()) != null){
            vModel.addAttribute("takenUsername", "Username already taken chief. ");
            return "/users/sign-up";

        } else if (user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getUsername().isEmpty()){
            vModel.addAttribute("missing", "Please fill out all forms." );
            return "/users/sign-up";
        }else {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            userDao.save(user);
            return "redirect:/login";

        }
    }
}
