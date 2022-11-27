package com.example.practice.UserAuth.repository;

import com.example.practice.UserAuth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserIdAndPassword(int userId, String password);
}
