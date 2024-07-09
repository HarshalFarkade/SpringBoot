package com.socialmedia.api.controller;

import com.socialmedia.api.entity.StoryEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.service.StoryService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/saveStory")
    public ResponseEntity<StoryEntity> saveStory(@RequestBody StoryEntity story ,
                                                 @RequestHeader("Authorization") String jwt) throws NoHandlerFoundException {
        UserEntity user = userService.findUserByJwt(jwt);

        StoryEntity saveStory= storyService.createStory(story, user.getId());
        return new ResponseEntity<>(saveStory, HttpStatus.CREATED);
    }

    @GetMapping("/api/getStoryById/{userId}")
    public ResponseEntity<List<StoryEntity>> getStoryById(@PathVariable Integer userId) throws NoHandlerFoundException {
        List<StoryEntity> story = storyService.findStoryByUserId(userId);
        return new ResponseEntity<List<StoryEntity>>(story,HttpStatus.OK);
    }
}
