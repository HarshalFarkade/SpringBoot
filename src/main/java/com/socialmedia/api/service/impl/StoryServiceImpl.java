package com.socialmedia.api.service.impl;

import com.socialmedia.api.entity.StoryEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.StoryRepo;
import com.socialmedia.api.service.StoryService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepo storyRepo;

    @Autowired
    private UserService userService;
    @Override
    public StoryEntity createStory(StoryEntity story, Integer userId) throws NoHandlerFoundException {
        try{
            UserEntity user = userService.getUserByID(userId);
            if(user != null){
                StoryEntity storyEntity =  new StoryEntity();
                storyEntity.setCaption(story.getCaption());
                storyEntity.setImage(story.getImage());
                storyEntity.setUser(user);
                storyEntity.setTimeStamp(LocalDateTime.now());
                return storyRepo.save(storyEntity);
            }else {
                throw new NoHandlerFoundException("User with this id is not found:"+userId,null,null);
            }
        }catch (Exception ex){
            throw ex;
        }

    }

    @Override
    public List<StoryEntity> findStoryByUserId(Integer userId) throws NoHandlerFoundException {
        try {
            UserEntity user = userService.getUserByID(userId);
            if(user !=null){
                return storyRepo.findByUserId(userId);
            }else {
                throw new NoHandlerFoundException("User with this id is not found:"+userId,null,null);
            }
        }catch (Exception ex){
            throw ex;
        }

    }
}
