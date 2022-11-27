package com.example.practice.UserAuth.services;

import com.example.practice.UserAuth.domain.User;
import com.example.practice.UserAuth.exception.UserNotFoundException;
import com.example.practice.UserAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(int userId) throws UserNotFoundException {

        boolean result = false;
        if(userRepository.findById(userId).isEmpty()) {
           throw new UserNotFoundException();
        }
        else {
            userRepository.deleteById(userId);
            result = true;
        }
        return result;
    }

    @Override
    public User findByUserIdAndPassword(int userId, String password) throws UserNotFoundException {

        User user = userRepository.findByUserIdAndPassword(userId,password);
        if(user == null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
