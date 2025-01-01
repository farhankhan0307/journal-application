package com.example.journalApp.services;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser (User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    public Optional<User> getUserById (ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteUser (ObjectId id) {
        userRepository.deleteById(id);
    }

    public User getUserByUsername (String username) {
        return userRepository.findByUsername(username);
    }
}
