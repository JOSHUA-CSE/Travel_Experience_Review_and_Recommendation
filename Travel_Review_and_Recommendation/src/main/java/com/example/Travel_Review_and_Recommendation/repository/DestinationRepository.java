package com.example.Travel_Review_and_Recommendation.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Travel_Review_and_Recommendation.model.Destination;
public interface DestinationRepository extends JpaRepository<Destination,Long>{
    
}
