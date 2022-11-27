package com.example.practice.UserAuth.services;

import com.example.practice.UserAuth.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<Integer, String> generateToken(User user);
}
