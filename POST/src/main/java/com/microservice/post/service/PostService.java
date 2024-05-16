package com.microservice.post.service;

import com.microservice.post.config.RestTemplateConfig;
import com.microservice.post.entity.Post;
import com.microservice.post.payload.PostDto;
import com.microservice.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplateConfig restTemplate;

    public Post savePost(Post post){

        String postId = UUID.randomUUID().toString();
        post.setId(postId);
        Post savedPost = postRepository.save(post);
        return savedPost;
    }


    public Post findPostById(String postId) {
        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        return post;
    }

    //this url:http://localhost:8082/api/comments/ is returing list that's why we r using ArrayList.class
    public PostDto getPostWithComments(String postId) {

        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        ArrayList comments = restTemplate.getRestTemplate().getForObject("http://localhost:8082/api/comments/"+postId, ArrayList.class);

        PostDto dto = new PostDto();
        dto.setPostId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getDescription());
        dto.setComments(comments);
        return dto;
    }
}
