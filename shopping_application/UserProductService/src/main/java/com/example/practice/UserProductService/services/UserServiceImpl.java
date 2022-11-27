package com.example.practice.UserProductService.services;

import com.example.practice.UserProductService.exception.ProductNotFoundException;
import com.example.practice.UserProductService.exception.UserAlreadyExistsException;
import com.example.practice.UserProductService.exception.UserNotFoundException;
import com.example.practice.UserProductService.model.Product;
import com.example.practice.UserProductService.model.User;
import com.example.practice.UserProductService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return userRepository.insert(user);
    }

    @Override
    public User addProductForUser(int userId, Product product) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findByUserId(userId);
        if(user.getProductList() == null){
            user.setProductList(Arrays.asList(product));
        }else {
            List<Product> products = user.getProductList();
            products.add(product);
            user.setProductList(products);
        }
        return userRepository.save(user);
    }

    @Override
    public User deleteProductForUser(int userId, int productId) throws UserNotFoundException, ProductNotFoundException {

        boolean result = false;
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        List<Product> products = user.getProductList();
        result = products.removeIf(x -> x.getProductId() == productId);
        if(!result){
            throw new ProductNotFoundException();
        }
        user.setProductList(products);
        return userRepository.save(user);
    }

    @Override
    public List<Product> getAllProductForUser(int userId) throws UserNotFoundException {
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId).get().getProductList();
    }
}
