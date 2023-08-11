package com.example.app.controller;

import com.example.app.entity.User;
import com.example.app.service.UserService;
import com.example.app.vo.ResponseTemplateVo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    //Fallback method for circuit breaker
    public ResponseTemplateVo fallBackMethod(Long userId, Throwable t){
        ResponseTemplateVo fallbackResponse = new ResponseTemplateVo();
        fallbackResponse.setUser(new User());
        fallbackResponse.setProduct(null);
        fallbackResponse.setMessage("User service is currently unavailable. Please try again later.");
        return fallbackResponse;
    }


    @PostMapping("/")
    public ResponseEntity<User> saveUsers(@RequestBody User user) {
        userService.saveUsers(user);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
    List<User>  userList =    userService.getAllUsers();

        return ResponseEntity.ok(userList);
    }

//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
//     User user =  userService.getUserById(id);
//
//      return ResponseEntity.ok(user);
//    }

    //VO Method

    @GetMapping("/{userId}")
    @CircuitBreaker(fallbackMethod = "fallBackMethod", name = "userApi")
    public ResponseTemplateVo getUserWithDepartment(@PathVariable Long userId){
       return Try.ofSupplier(() -> userService.getUserWithDepartment(userId))
                 .getOrElseThrow(throwable -> new RuntimeException("No such Id"));
    }


}
