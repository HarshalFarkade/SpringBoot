package com.socialmedia.api.controller;

import com.socialmedia.api.entity.CommentEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.service.CommentService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/comment/post/{postId}")
    public ResponseEntity<CommentEntity> createComment(@RequestBody CommentEntity comment,
                                                       @RequestHeader("Authorization") String jwt,
                                                       @PathVariable Integer postId) throws NoHandlerFoundException {
        UserEntity user = userService.findUserByJwt(jwt);
        CommentEntity createComment = commentService.createComment(comment, postId, user.getId());
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @PutMapping("/api/comments/like/{commentId}")
    public ResponseEntity<CommentEntity> likeComment(@PathVariable Integer commentId,
                                                     @RequestHeader("Authorization")String jwt) throws Exception {

        UserEntity user = userService.findUserByJwt(jwt);
        CommentEntity likeCom=commentService.likeComment(commentId, user.getId());
        return new ResponseEntity<>(likeCom,HttpStatus.OK);
    }

    @GetMapping("/api/comment/findById/{commentId}")
    public ResponseEntity<CommentEntity> getCommentById(@PathVariable Integer commentId) throws NoHandlerFoundException{
        CommentEntity  comment = commentService.findCommentById(commentId);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }
}
