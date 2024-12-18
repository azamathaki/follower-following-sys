package com.portfolio.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String bio;

    private String username;

    private String password;

    // Users I am following
    @ManyToMany
    @JoinTable(
        name = "connects", // Join table name
        joinColumns = @JoinColumn(name = "follower_id"), // My ID as the follower
        inverseJoinColumns = @JoinColumn(name = "followed_id") // The user's ID that I follow
    )
    
    private Set<User> following = new HashSet<>();

    // Users who follow me
    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    // Constructors
    public User() {}

    public User(String fullName, String bio, String username, String password) {
        this.fullName = fullName;
        this.bio = bio;
        this.username = username;
        this.password = password;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    // Helper methods to manage relationships
    public void follow(User user) {
        this.following.add(user);
        user.getFollowers().add(this);
    }

    public void unfollow(User user) {
        this.following.remove(user);
        user.getFollowers().remove(this);
    }
}

