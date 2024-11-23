package com.amit.prod_ready_features_auditing.services;

import com.amit.prod_ready_features_auditing.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();
    PostDto createNewPost(PostDto postDto);
    PostDto getPostById(Long PostId);
    PostDto updatePost(PostDto postDto,Long postId);
}
