package com.portfolio.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.demo.model.User;
import com.portfolio.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    

    @Autowired
    private UserService service;

    // create an user
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return service.saveUser(user);
    }

    // get an existing user
    @GetMapping("/users/{id}")
    public User getAnUser(@PathVariable("id") Long id){
        return service.getUser(id);
    }
    

    // following an user
    @PutMapping("/users/{id}/follow/{followingId}")
    public User followUser(@PathVariable("id") Long id, 
                            @PathVariable("followingId") Long followingId)
    {
        return service.follow(id,followingId);
    }

    // unfollowing an followed user
    @PutMapping("/users/{id}/unfollow/{followingId}")
    public User unfollowUser(@PathVariable("id") Long id, 
                            @PathVariable("followingId") Long followingId)
    {
        return service.unfollow(id,followingId);
    }

}
