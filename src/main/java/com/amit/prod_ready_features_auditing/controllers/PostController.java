package com.amit.prod_ready_features_auditing.controllers;


import com.amit.prod_ready_features_auditing.dto.PostDto;
import com.amit.prod_ready_features_auditing.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost) {
        return postService.createNewPost(inputPost);
    }


    @PutMapping("/{postId}")
    public PostDto updatesPost(@RequestBody PostDto postDto, @PathVariable Long postId){
        return postService.updatePost(postDto,postId);
    }

}
