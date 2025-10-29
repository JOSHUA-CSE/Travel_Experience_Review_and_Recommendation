package com.example.Travel_Review_and_Recommendation.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Travel_Review_and_Recommendation.dto.LoginRequest;
import com.example.Travel_Review_and_Recommendation.model.User;
import com.example.Travel_Review_and_Recommendation.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository user_repo;

    public Optional<User> getUserById(Long id) {
        return user_repo.findById(id);
    }

    public Optional<User> getUserByEmail(LoginRequest req) {
        return user_repo.findByEmail(req.getEmail());
    }

    public User createUser(User user) {
        return user_repo.save(user);
    }
}
