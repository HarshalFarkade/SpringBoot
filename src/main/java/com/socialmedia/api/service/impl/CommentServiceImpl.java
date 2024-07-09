package com.socialmedia.api.service.impl;

import com.socialmedia.api.entity.CommentEntity;
import com.socialmedia.api.entity.PostEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.CommentRepo;
import com.socialmedia.api.repository.PostRepo;
import com.socialmedia.api.service.CommentService;
import com.socialmedia.api.service.PostService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
     private PostService postService;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepo commentRepo;

    @Override
    public CommentEntity createComment(CommentEntity comment, Integer postId, Integer userId) throws NoHandlerFoundException {
        try {

            UserEntity user = userService.getUserByID(userId);
            if (user == null) {
                throw new NoHandlerFoundException("User with ID " + userId + " not found", null, null);
            }

            Optional<PostEntity> optionalPost = postService.findPostById(postId);
            if (!optionalPost.isPresent()) {
                throw new NoHandlerFoundException("Post with ID " + postId + " not found", null, null);
            }
            PostEntity post = optionalPost.get();


            comment.setUser(user);
            comment.setCreatedAt(LocalDateTime.now());


            CommentEntity savedComment = commentRepo.save(comment);

            post.getComments().add(savedComment);

            postRepo.save(post);

            return savedComment;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public CommentEntity findCommentById(Integer commentId) throws NoHandlerFoundException {
        try{
            Optional<CommentEntity> opt =  commentRepo.findById(commentId);
            if(opt.isPresent()){
                return opt.get();
            }else {
                throw new NoHandlerFoundException("Comment with this id is not found"+commentId,null,null);
            }
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Override
    public CommentEntity likeComment(Integer commentId, Integer userId) throws Exception {
        try{
             CommentEntity comment=findCommentById(commentId);
             UserEntity user =userService.getUserByID(userId);
             if(!comment.getLike().contains(user)){
                 comment.getLike().add(user);
             }else {
                 comment.getLike().remove(user);
             }
             return commentRepo.save(comment);
        }catch (Exception ex){
            throw  ex;
        }

    }
}
