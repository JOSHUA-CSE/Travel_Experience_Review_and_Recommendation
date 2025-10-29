package com.example.Travel_Review_and_Recommendation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.Travel_Review_and_Recommendation.model.User;
import com.example.Travel_Review_and_Recommendation.dto.LoginRequest;
import com.example.Travel_Review_and_Recommendation.Service.UserService;
import com.example.Travel_Review_and_Recommendation.Service.ReviewService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews/list";
    }
}
