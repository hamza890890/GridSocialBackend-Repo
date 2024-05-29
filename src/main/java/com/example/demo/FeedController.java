package com.example.demo;

//package demo.example.controller;

//import demo.example.model.Post;
//import demo.example.model.User;
//import demo.example.service.PostService;
//import demo.example.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;

    @GetMapping
    public String getFeed(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam String content, @RequestParam User user) {
        postService.createPost(content, user);
        return "redirect:/feed";
    }

    @PostMapping("/like")
    public String likePost(@RequestParam Long postId, @RequestParam User user) {
        Post post = postService.getPostById(postId);
        likeService.likePost(user, post);
        return "redirect:/feed";
    }

    @PostMapping("/unlike")
    public String unlikePost(@RequestParam Long postId, @RequestParam User user) {
        Post post = postService.getPostById(postId);
        likeService.unlikePost(user, post);
        return "redirect:/feed";
    }
}

