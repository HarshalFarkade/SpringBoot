package com.socialmedia.api.service;

import com.socialmedia.api.entity.CommentEntity;
import org.springframework.web.servlet.NoHandlerFoundException;

public interface CommentService {

    CommentEntity createComment(CommentEntity comment,
                                Integer postId,
                                Integer userId) throws NoHandlerFoundException;

    CommentEntity findCommentById(Integer commentId) throws NoHandlerFoundException;

    CommentEntity likeComment (Integer commentId, Integer userId) throws Exception;


}
