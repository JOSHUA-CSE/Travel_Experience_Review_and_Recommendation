package com.example.Travel_Review_and_Recommendation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.Travel_Review_and_Recommendation.Service.DestinationService;
import com.example.Travel_Review_and_Recommendation.Service.UserService;
import com.example.Travel_Review_and_Recommendation.model.Destination;
import com.example.Travel_Review_and_Recommendation.model.User;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    @Autowired
    private DestinationService d_service;
    @Autowired
    private UserService u_service;
    @GetMapping
    public String getAllDestinations(Model model) {
        model.addAttribute("destinations",d_service.getAllDestinations());
        return "destination/list";
    }

    @PostMapping("/create/{userId}")
    public String createDestination(@PathVariable Long userId, @RequestBody Destination destination) {
        User user=u_service.getUserById(userId).orElse(null);

        if (user==null) {
            return "User not found";
        }
        if (!"ADMIN".equalsIgnoreCase(user.getRole())){
            return "Access denied! Only admins can create destinations.";
        }
        d_service.createDestination(destination);
        return "Destination created successfully!";
    }


    @GetMapping("/{id}")
    public String getAllDestinations(@PathVariable Long id, Model model) {
        model.addAttribute("Destination",d_service.getDestinationById(id));
        return "destination/list";
    }
    
}
