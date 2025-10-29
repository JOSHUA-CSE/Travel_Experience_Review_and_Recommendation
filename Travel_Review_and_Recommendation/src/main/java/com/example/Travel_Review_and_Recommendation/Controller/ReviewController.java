package com.example.Travel_Review_and_Recommendation.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.Travel_Review_and_Recommendation.Service.ReviewService;
import com.example.Travel_Review_and_Recommendation.dto.ReviewRequest;
import com.example.Travel_Review_and_Recommendation.model.Review;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService r_service;

    @PostMapping
    public Object submitReview(@RequestBody ReviewRequest req) {
        if (req.getUserId()==null|| req.getDestinationId()==null) {
            return "userId and destinationId are required";
        }
        Review review=new Review();
        review.setRating(req.getRating());
        review.setComment(req.getComment());
        Review saved=r_service.createReview(req.getUserId(),req.getDestinationId(),review);
        if (saved==null) {
            return "User or Destination not found";
        }
        return saved;
    }
    @GetMapping("/{destinationId}")
    public List<Review> getReviewsForDestination(@PathVariable("destinationId") Long destinationId) {
        return r_service.getReviewsByDestinationId(destinationId);
    }

    @GetMapping
    public List<Review> getAllReviews(){
        return r_service.getAllReviews();
    }

    @PutMapping("/{id}")
    public Object editReview(@PathVariable Long id, @RequestBody ReviewRequest req) {
        Review updated=new Review();
        updated.setRating(req.getRating());
        updated.setComment(req.getComment());
        Optional<Review> updatedOpt=r_service.updateReview(id, updated);
        if (updatedOpt.isPresent()) {
            return updatedOpt.get();
        }
        return "Review not found";
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        if (role==null|| !"ADMIN".equalsIgnoreCase(role)) {
            return "Admin role required";
        }
        boolean deleted=r_service.deleteReview(id);
        if (deleted) {
            return "Review deleted";
        }
        return "Review not found";
    }

     @PostMapping(path = "/submit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView submitReviewFormFromPage(@RequestParam("userId") Long userId,
            @RequestParam("destinationId") Long destinationId,
            @RequestParam("rating") float rating,
            @RequestParam("comment") String comment,
            RedirectAttributes redirectAttributes) {
        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        Review saved = r_service.createReview(userId, destinationId, review);
        if (saved == null) {
            redirectAttributes.addFlashAttribute("error", "User or Destination not found");
        } else {
            redirectAttributes.addFlashAttribute("message", "Review submitted");
        }
        return new RedirectView("/api/destinations/" + destinationId);
    }
}
