package com.socialmedia.api.service;

import com.socialmedia.api.entity.StoryEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Service
public interface StoryService {

    StoryEntity createStory(StoryEntity story ,Integer userId) throws NoHandlerFoundException;

    List<StoryEntity> findStoryByUserId(Integer userId) throws NoHandlerFoundException;
}
