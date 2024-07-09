package com.socialmedia.api.service;

import com.socialmedia.api.entity.PostEntity;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostEntity createNewPost(PostEntity post , Integer userId) throws NoHandlerFoundException;

    String deletePostByID(Integer postId,Integer userId)throws NoHandlerFoundException;

    List<PostEntity> findPostByUserId(Integer userId) throws NoHandlerFoundException;

    Optional<PostEntity> findPostById(Integer postId) throws NoHandlerFoundException;

    List<PostEntity> findAllPost();

    PostEntity savedPost(Integer  postId , Integer userId) throws NoHandlerFoundException;

    PostEntity likePost(Integer postId, Integer  userId) throws NoHandlerFoundException;

}
