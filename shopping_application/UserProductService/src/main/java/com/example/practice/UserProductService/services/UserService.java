package com.example.practice.UserProductService.services;

import com.example.practice.UserProductService.exception.ProductNotFoundException;
import com.example.practice.UserProductService.exception.UserAlreadyExistsException;
import com.example.practice.UserProductService.exception.UserNotFoundException;
import com.example.practice.UserProductService.model.Product;
import com.example.practice.UserProductService.model.User;

import java.util.List;


public interface UserService {

    public abstract User addUser(User user) throws UserAlreadyExistsException;

    public abstract User addProductForUser(int userId, Product product) throws UserNotFoundException;

    public abstract User deleteProductForUser(int userId, int productId) throws UserNotFoundException, ProductNotFoundException;

    public abstract List<Product> getAllProductForUser(int userId) throws UserNotFoundException;
}
