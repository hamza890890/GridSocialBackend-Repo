package com.example.demo;

//package demo.example.service;

//import demo.example.model.Like;
//import demo.example.model.Post;
//import demo.example.model.User;
//import demo.example.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public Like likePost(User user, Post post) {
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        return likeRepository.save(like);
    }

    public void unlikePost(User user, Post post) {
        Like like = likeRepository.findByUserIdAndPostId(user.getId(), post.getId());
        if (like != null) {
            likeRepository.delete(like);
        }
    }
}

