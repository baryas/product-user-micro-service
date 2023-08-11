package com.example.app.service;

import com.example.app.entity.User;
import com.example.app.repository.UserRepository;
import com.example.app.vo.Product;
import com.example.app.vo.ResponseTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUsers(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such Id"));
    }

    public ResponseTemplateVo getUserWithDepartment(Long userId) {

        ResponseTemplateVo vo =
                new ResponseTemplateVo();

        User user =
                userRepository.findByUserId(userId);

        Product product =
                restTemplate.getForObject("http://PRODUCT-SERVICE/products/" +
                                            user.getProductId(), Product.class);


        vo.setProduct(product);
        vo.setUser(user);

        return vo;

    }


}
