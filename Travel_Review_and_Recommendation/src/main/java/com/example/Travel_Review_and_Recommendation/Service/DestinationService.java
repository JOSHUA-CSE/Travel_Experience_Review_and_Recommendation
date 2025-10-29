package com.example.Travel_Review_and_Recommendation.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Travel_Review_and_Recommendation.repository.DestinationRepository;
import com.example.Travel_Review_and_Recommendation.model.Destination;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository dec_repo;

    public List<Destination> getAllDestinations() {
        return dec_repo.findAll();
    }

    public Destination createDestination(Destination d) {
        return dec_repo.save(d);
    }

    public Destination getDestinationById(Long id) {
        return dec_repo.findById(id).orElse(null);
    }
}
