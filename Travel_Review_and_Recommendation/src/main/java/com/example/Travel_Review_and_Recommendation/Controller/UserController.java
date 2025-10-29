package com.example.Travel_Review_and_Recommendation.Controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Travel_Review_and_Recommendation.model.User;
import com.example.Travel_Review_and_Recommendation.Service.UserService;
import com.example.Travel_Review_and_Recommendation.dto.LoginRequest;
import com.example.Travel_Review_and_Recommendation.dto.UserResponse;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService u_service;

    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {
        User saved = u_service.createUser(user);
        return new UserResponse(saved.getUser_id(),saved.getName(),saved.getEmail(),saved.getRole());
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginRequest req) {
        Optional<User> u=u_service.getUserByEmail(req);
        if (!req.getPassword().equals(u.get().getPassword())) {
            return "Invalid Password";
        }
        User user=u.get();
        return new UserResponse(user.getUser_id(),user.getName(),user.getEmail(),user.getRole());
    }
    @GetMapping("/users/{id}")
    public Object getUser(@PathVariable Long id) {
        Optional<User> u=u_service.getUserById(id);
        return u.map(user->(Object)new UserResponse(user.getUser_id(),user.getName(),user.getEmail(),user.getRole()))
                .orElse((Object)"User not found");
    }
}
