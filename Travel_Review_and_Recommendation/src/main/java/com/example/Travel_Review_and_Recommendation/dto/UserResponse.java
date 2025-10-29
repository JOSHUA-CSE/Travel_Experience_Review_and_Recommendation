package com.example.Travel_Review_and_Recommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long user_id;
    private String name;
    private String email;
    private String role;
}
