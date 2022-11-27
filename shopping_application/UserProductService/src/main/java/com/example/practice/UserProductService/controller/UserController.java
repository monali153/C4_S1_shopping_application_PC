package com.example.practice.UserProductService.controller;

import com.example.practice.UserProductService.exception.ProductNotFoundException;
import com.example.practice.UserProductService.exception.UserAlreadyExistsException;
import com.example.practice.UserProductService.exception.UserNotFoundException;
import com.example.practice.UserProductService.model.Product;
import com.example.practice.UserProductService.model.User;
import com.example.practice.UserProductService.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("userproduct/app")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            user.setProductList(new ArrayList<>());
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException ex){
            throw new UserAlreadyExistsException();
        }
    }

    @PutMapping("addproduct/{userId}")
    public ResponseEntity<?> addProduct(@PathVariable int userId, @RequestBody Product product) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userService.addProductForUser(userId,product),HttpStatus.CREATED);
        }catch (UserNotFoundException ex){
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping("deleteproduct/{userId}/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int userId, @PathVariable int productId) throws UserNotFoundException, ProductNotFoundException {
        try {
            return new ResponseEntity<>(userService.deleteProductForUser(userId,productId),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException();
        }
    }

    @GetMapping("getproduct/{userId}")
    public ResponseEntity<?> getProduct(@PathVariable int userId) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(userService.getAllProductForUser(userId),HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
    }
}
