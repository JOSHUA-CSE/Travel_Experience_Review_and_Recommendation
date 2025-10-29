package com.example.Travel_Review_and_Recommendation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Destination {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long destination_id;

    private String name;
    private String description;
    private String location;
    private String image_url;
}
