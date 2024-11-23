package com.amit.prod_ready_features_auditing.services;

import com.amit.prod_ready_features_auditing.dto.PostDto;
import com.amit.prod_ready_features_auditing.entities.PostEntity;
import com.amit.prod_ready_features_auditing.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto postDto) {
        PostEntity postEntity=modelMapper.map(postDto,PostEntity.class);

        return modelMapper.map(postRepository.save(postEntity),PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity=postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with id "+postId));
        return modelMapper.map(postEntity,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long postId) {
        PostEntity postEntity=postRepository
                .findById(postId).orElseThrow(()->new ResourceNotFoundException("post not found with id "+postId));
                postDto.setId(postId);
                modelMapper.map(postDto,postEntity);
                PostEntity savedPostEntity=postRepository.save(postEntity);

        return modelMapper.map(savedPostEntity,PostDto.class);
    }
}
