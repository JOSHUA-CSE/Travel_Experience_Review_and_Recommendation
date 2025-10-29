package com.example.Travel_Review_and_Recommendation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Travel_Review_and_Recommendation.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.destination.destination_id = :destinationId")
    List<Review> findByDestinationId(@Param("destinationId") Long destinationId);
}
