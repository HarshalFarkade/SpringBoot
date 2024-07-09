package com.socialmedia.api.controller;

import com.socialmedia.api.entity.PostEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.reponse.ApiResponse;
import com.socialmedia.api.service.PostService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    // crate post using userId
    @PostMapping("/api/createNewPost")
    public ResponseEntity<PostEntity> createPost(@RequestHeader("Authorization") String jwt
                                                ,@RequestBody PostEntity postEntity)throws NoHandlerFoundException{

        UserEntity reqUser = userService.findUserByJwt(jwt);
        PostEntity post = postService.createNewPost(postEntity,reqUser.getId());
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    // delete post using userid and postId
    @DeleteMapping("/api/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt)throws NoHandlerFoundException{
        UserEntity reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePostByID(postId, reqUser.getId());
         ApiResponse response = new ApiResponse(message,true);
        return new  ResponseEntity<ApiResponse>(response,HttpStatus.OK);
    }

    //find post by using postId;
    @GetMapping("/api/getPostById/{postId}")
    public ResponseEntity<PostEntity>findPostById(@PathVariable Integer postId)throws NoHandlerFoundException{
        PostEntity post = postService.findPostById(postId).get();
        return new ResponseEntity<PostEntity>(post,HttpStatus.ACCEPTED);

    }

    // find post by userId;
    @GetMapping("/api/getPostByUserId/{userId}")
    public ResponseEntity<List<PostEntity>> findUsersPost(@PathVariable Integer userId)throws NoHandlerFoundException{
        List<PostEntity> post = postService.findPostByUserId(userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // get all post method ;
    @GetMapping("/api/getAllPost")
    public ResponseEntity<List<PostEntity>> findAllPost(){
        List<PostEntity> post = postService.findAllPost();

        return new ResponseEntity<List<PostEntity>>(post,HttpStatus.OK);
    }


    // save post using postId and UserID;
    @PutMapping("/api/savePost/post/{postId}")
    public ResponseEntity<PostEntity> savePostUsingPostIdAndUserID(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt)throws NoHandlerFoundException{

        UserEntity reqUser = userService.findUserByJwt(jwt);
        PostEntity post = postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<PostEntity>(post,HttpStatus.ACCEPTED);
    }

    // like post using postId,userId;
    @PutMapping("/api/like/{postId}")
    public ResponseEntity<PostEntity> likePostHandler(@PathVariable Integer postId,  @RequestHeader("Authorization") String jwt)throws NoHandlerFoundException{

        UserEntity reqUser  = userService.findUserByJwt(jwt);
        PostEntity post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<PostEntity>(post,HttpStatus.ACCEPTED);
    }


}
