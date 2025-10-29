package com.example.Travel_Review_and_Recommendation.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class ReviewRequest {
    
    @JsonAlias({"user_id"})
    private Long userId;

    @JsonAlias({"destination_id"})
    private Long destinationId;

    private float rating;
    private String comment;
}
