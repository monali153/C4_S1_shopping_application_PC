package com.example.practice.UserAuth.services;

import com.example.practice.UserAuth.domain.User;
import com.example.practice.UserAuth.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    public User addUser(User user);
    public List<User> getAllUser();
    public boolean deleteUser(int userId) throws UserNotFoundException;
    public User findByUserIdAndPassword(int userId, String password) throws UserNotFoundException;
}
