package com.example.practice.UserProductService.repository;

import com.example.practice.UserProductService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Integer> {

    public User findByUserId(int userId);
}
