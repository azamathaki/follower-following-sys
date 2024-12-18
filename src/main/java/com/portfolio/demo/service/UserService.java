package com.portfolio.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.demo.model.User;
import com.portfolio.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;


    public User saveUser(User user){
        return repository.save(user);
    }

    public User getUser(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("user not found with ID: " + id));
    }

    public User follow(Long id, Long followingId) {
        User user =  repository.findById(id).orElseThrow(()-> new RuntimeException("user not found with ID: " + id));
        User followingUser = repository.findById(id).orElseThrow(()-> new RuntimeException("user not found with ID: " + followingId));

        if (!id.equals(followingId)){
            user.follow(followingUser);

            repository.save(user);
            repository.save(followingUser);
            return user;
        }else {
            throw new RuntimeException("Same id to follow!");
        }

    }

    public User unfollow(Long id, Long followingId) {
        User user =  repository.findById(id).orElseThrow(()-> new RuntimeException("user not found with ID: " + id));
        User followingUser = repository.findById(id).orElseThrow(()-> new RuntimeException("user not found with ID: " + followingId));


        user.getFollowing().remove(followingUser);
        followingUser.getFollowers().remove(user);

        user.unfollow(followingUser);
        repository.save(user);
        repository.save(followingUser);
        return user;
    }


    
}
