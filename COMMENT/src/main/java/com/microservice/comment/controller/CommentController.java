package com.microservice.comment.controller;

import com.microservice.comment.entity.Comment;
import com.microservice.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //http://localhost:8082/api/comments

    //http://localhost:8082/api/comments this url will call commentController
    //CC will call commentService ,CS will use restTemplate
    //resttemplate with help of this url http://localhost:8082/api/posts ,gets the postId
    //based on postId it will get Post Object
    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        Comment comment1 = commentService.saveComment(comment);
        return new ResponseEntity<>(comment1, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public List<Comment> getAllCommentsByPostId(@PathVariable String postId){
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        return comments;
    }
}
