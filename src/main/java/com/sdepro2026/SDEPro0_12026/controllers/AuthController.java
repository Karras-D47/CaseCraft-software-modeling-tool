package com.sdepro2026.SDEPro0_12026.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sdepro2026.SDEPro0_12026.domain.User;
import com.sdepro2026.SDEPro0_12026.services.UserService;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username,@RequestParam String email,@RequestParam String password,RedirectAttributes redirectAttributes) {
        if(userService.usernameExists(username)){
            redirectAttributes.addFlashAttribute("error","Username already taken");
            return "redirect:/register";
        }
        userService.registerUser(username, email, password);
        redirectAttributes.addFlashAttribute("success","Account created please log in.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "auth/login";
    }

    @GetMapping("/profile")
    public String showProfileForm(@AuthenticationPrincipal com.sdepro2026.SDEPro0_12026.domain.User currentUser,Model model){
        model.addAttribute("user",currentUser);
        return "auth/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
                            @RequestParam String email,
                            @RequestParam(required = false) String password,
                            RedirectAttributes redirectAttributes) {
    User currentUser = userService.findByUsername(principal.getUsername());
    userService.updateProfile(currentUser, email, password);
    redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
    return "redirect:/profile";
}

    
}
