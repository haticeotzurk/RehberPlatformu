package com.finalproje.RehberPlatformu.controller;

import com.finalproje.RehberPlatformu.entity.User;
import com.finalproje.RehberPlatformu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    // --- 1. Giriş Sayfası Gösterimi ---
    // Spring Security, /login POST isteğini otomatik olarak yönetir.
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }

    // --- 2. Kayıt Sayfası Gösterimi ---
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // register.html
    }

    // --- 3. Kayıt İşlemi ---
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.registerUser(user);
            // Kayıt başarılı, giriş sayfasına yönlendir
            return "redirect:/login?success"; 
        } catch (RuntimeException e) {
            // Hata durumunda (kullanıcı adı mevcut vb.)
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user); // Kullanıcının girdiği verileri koru
            return "register";
        }
    }
}