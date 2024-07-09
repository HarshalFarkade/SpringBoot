package com.socialmedia.api.service.impl;

import com.socialmedia.api.entity.PostEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.PostRepo;
import com.socialmedia.api.repository.UserRepo;
import com.socialmedia.api.service.PostService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo  postRepo;

    @Autowired
    UserService  userService;

    @Autowired
    UserRepo userRepo;


    @Override
    public PostEntity createNewPost(PostEntity post, Integer userId) throws NoHandlerFoundException {

        try{
            UserEntity user = userService.getUserByID(userId);
            if(user!= null){
//            set user
                PostEntity postEntity = new PostEntity();

                postEntity.setUserEntity(user);

                postEntity.setCaption(post.getCaption());
                postEntity.setImage(post.getImage());
                postEntity.setCreateAt(LocalDateTime.now());
                postEntity.setVideo(post.getVideo());
                return  postRepo.save(postEntity);

            }else {
                throw new NoHandlerFoundException("user with id:" +userId,"is not found",null);
            }

        }catch (Exception ex){
            throw ex;
        }

    }

    @Override
    public String deletePostByID(Integer postId, Integer userId) throws NoHandlerFoundException {
        try {
            PostEntity post = postRepo.findById(postId).get();
            UserEntity userEntityOp=userService.getUserByID(userId);

            if(post.getUserEntity().getId()  != userEntityOp.getId()){
                throw new NoHandlerFoundException("post with user id is not found","can no delete another user",null);
            }else{
                postRepo.delete(post);
                 return "Post delete Siccessfully";
            }
        }catch (Exception ex){
          throw ex;
        }
    }

    @Override
    public List<PostEntity> findPostByUserId(Integer userId) throws NoHandlerFoundException {
        try {
            List<PostEntity> postEntities = postRepo.findPostByUserId(userId);
            if(postEntities.isEmpty()){
                throw new NoHandlerFoundException("No posts found for user ID: " + userId, "/posts/user/" + userId, null);
            }
            return postEntities;
        } catch (Exception ex) {
            // Handle any other exceptions here
            throw ex;
        }
    }

    @Override
    public Optional<PostEntity> findPostById(Integer postId) throws NoHandlerFoundException {
        try {
            Optional<PostEntity> post = postRepo.findById(postId);
            if(post.isPresent()){
                return postRepo.findById(postId);
            }else {
                throw new NoHandlerFoundException("Post with id :"+postId,"is  not found",null);
            }
        }catch (Exception ex){
           throw ex;
        }

    }

    @Override
    public List<PostEntity> findAllPost() {
        return postRepo.findAll();
    }

    @Override
    public PostEntity savedPost(Integer postId, Integer userId) throws NoHandlerFoundException {
        try {
            PostEntity post = findPostById(postId).get();
            UserEntity user = userRepo.findById(userId).get(); // Use orElse(null) to handle optional

            if (user != null) {
                if (user.getSavePost().contains(post)) {
                    user.getSavePost().remove(post);
                } else {
                    user.getSavePost().add(post);
                }
                userRepo.save(user); // Save user entity
                return post; // Return post entity
            } else {
                throw new NoHandlerFoundException("User with id " + userId + " not found", null, null);
            }
        } catch (NoHandlerFoundException ex) {
            throw new NoHandlerFoundException("Post and user not found with id " + postId + " and " + userId, null, null);
        }
    }


    @Override
    public PostEntity likePost(Integer postId, Integer reqUserId) throws NoHandlerFoundException {
        try {
            // Fetch the post by ID
            PostEntity post = postRepo.findById(postId)
                    .orElseThrow(() -> new NoHandlerFoundException("Post with ID: " + postId + " not found", null, null));

            // Fetch the user by ID
            UserEntity reqUser = userService.getUserByID(reqUserId);
            if (reqUser == null) {
                throw new NoHandlerFoundException("User with ID: " + reqUserId + " not found", null, null);
            }

            // Check if the user has already liked the post
            if (post.getLiked().contains(reqUser)) {
                // User already liked the post, remove the like
                post.getLiked().remove(reqUser);
            } else {
                // User has not liked the post, add the like
                post.getLiked().add(reqUser);
            }

            // Save the updated post
            return postRepo.save(post);
        } catch (NoHandlerFoundException ex) {
            // Re-throw the NoHandlerFoundException to propagate it up the call stack
            throw ex;
        } catch (Exception ex) {
            // Catch any other exceptions and wrap them in a NoHandlerFoundException with a generic error message
            throw new NoHandlerFoundException("An error occurred while processing the request", null,null);
        }
    }


}
