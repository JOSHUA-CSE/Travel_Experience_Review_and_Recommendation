package com.example.Travel_Review_and_Recommendation.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Travel_Review_and_Recommendation.model.Destination;
import com.example.Travel_Review_and_Recommendation.model.Review;
import com.example.Travel_Review_and_Recommendation.model.User;
import com.example.Travel_Review_and_Recommendation.repository.DestinationRepository;
import com.example.Travel_Review_and_Recommendation.repository.ReviewRepository;
import com.example.Travel_Review_and_Recommendation.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DestinationRepository destinationRepo;

    public Review createReview(Long userId, Long destinationId, Review review) {
        Optional<User> u = userRepo.findById(userId);
        Optional<Destination> d = destinationRepo.findById(destinationId);
        if (!u.isPresent() || !d.isPresent()) {
            return null;
        }
        review.setUser(u.get());
        review.setDestination(d.get());
        return reviewRepo.save(review);
    }

    public List<Review> getReviewsByDestinationId(Long destinationId) {
        return reviewRepo.findByDestinationId(destinationId);
    }
    public List<Review> getAllReviews() {
        return reviewRepo.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepo.findById(id);
    }

    public Optional<Review> updateReview(Long id, Review updated) {
        Optional<Review> existing = reviewRepo.findById(id);
        if (existing.isPresent()) {
            Review r = existing.get();
            r.setRating(updated.getRating());
            r.setComment(updated.getComment());
            return Optional.of(reviewRepo.save(r));
        }
        return Optional.empty();
    }

    public boolean deleteReview(Long id) {
        if (reviewRepo.existsById(id)) {
            reviewRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
