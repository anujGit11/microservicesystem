package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateConfig restTemplate;


    //GETFOROBJECT WILL GO TO THIS URL and based on the postId , it will get the Post Object and post object content will store in the POST(PAYLOAD)

    //http://localhost:8082/api/comments this url will call commentController
    //CC will call commentService ,CS will use restTemplate
    //resttemplate with help of this url http://localhost:8082/api/posts ,gets the postId
    //based on postId it will get Post Object



    public Comment saveComment(Comment comment){

//        Post post = restTemplate.getRestTemplate().getForObject
//                ("http://localhost:8081/api/posts/"+comment.getPostId(), Post.class);

        Post post = restTemplate.getRestTemplate().getForObject("http://localhost:8081/api/posts/"+comment.getPostId(), Post.class);

        //Now POST-SERVICE replace the port number at all the places as per YML file
        if(post!=null){
            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);
            Comment savedComment = commentRepository.save(comment);
            return savedComment;
        }else{
            return  null;
        }

    }

    public List<Comment> getAllCommentsByPostId(String postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }
}
