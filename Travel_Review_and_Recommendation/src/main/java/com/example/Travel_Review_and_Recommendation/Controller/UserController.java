package com.example.Travel_Review_and_Recommendation.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.Travel_Review_and_Recommendation.model.User;
import com.example.Travel_Review_and_Recommendation.Service.UserService;
import com.example.Travel_Review_and_Recommendation.dto.LoginRequest;
import com.example.Travel_Review_and_Recommendation.dto.UserResponse;

@Controller
public class UserController {

    @Autowired
    private UserService u_service;

    @PostMapping("/api/users")
    @ResponseBody
    public UserResponse register(@RequestBody User user) {
        User saved = u_service.createUser(user);
        return new UserResponse(saved.getUser_id(), saved.getName(), saved.getEmail(), saved.getRole());
    }

    @PostMapping("/api/login")
    @ResponseBody
    public Object login(@RequestBody LoginRequest req) {
        Optional<User> u = u_service.getUserByEmail(req);
        if (!u.isPresent()) {
            return "User not found";
        }
        if (!req.getPassword().equals(u.get().getPassword())) {
            return "Invalid Password";
        }
        User user = u.get();
        return new UserResponse(user.getUser_id(), user.getName(), user.getEmail(), user.getRole());
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public Object getUser(@PathVariable Long id) {
        Optional<User> u = u_service.getUserById(id);
        return u.map(user -> (Object) new UserResponse(user.getUser_id(), user.getName(), user.getEmail(), user.getRole()))
                .orElse((Object) "User not found");
    }
    
    @GetMapping("/users/{id}")
    public String userProfile(@PathVariable Long id, Model model) {
        Optional<User> u = u_service.getUserById(id);
        if (!u.isPresent()) {
            // If user not found, show home page (minimal behavior)
            return "home";
        }
        model.addAttribute("user", u.get());
        return "users/profile";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }
    @PostMapping("/register")
    public RedirectView registerUser(@ModelAttribute User user, RedirectAttributes attrs) {
        u_service.createUser(user);
        attrs.addFlashAttribute("message", "Account created successfully");
        return new RedirectView("/login");
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "users/login";
    }
    @PostMapping("/login")
    public RedirectView loginUser(@ModelAttribute LoginRequest req, RedirectAttributes attrs) {
        Optional<User> u = u_service.getUserByEmail(req);
        if (!u.isPresent()) {
            attrs.addFlashAttribute("error", "User not found");
            return new RedirectView("/login");
        }
        if (!req.getPassword().equals(u.get().getPassword())) {
            attrs.addFlashAttribute("error", "Invalid password");
            return new RedirectView("/login");
        }
        return new RedirectView("/users/" + u.get().getUser_id());
    }
}
