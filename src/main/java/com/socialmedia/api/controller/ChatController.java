package com.socialmedia.api.controller;

import com.socialmedia.api.entity.ChatEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.request.ChatRequest;
import com.socialmedia.api.service.ChatService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public ResponseEntity<ChatEntity> createChat(
            @RequestHeader("Authorization") String jwt,
            @RequestBody ChatRequest request) throws NoHandlerFoundException {

        UserEntity reqUser = userService.findUserByJwt(jwt);
        // Assuming request.getUserId() returns the ID of the second user
        UserEntity userId2 = userService.getUserByID(request.getUserId());
        return new ResponseEntity<>(chatService.createChat(reqUser, userId2), HttpStatus.CREATED);
    }



    @GetMapping("/api/chats/getChatsByUser")
    public ResponseEntity<List<ChatEntity>>findUsersChat(@RequestHeader("Authorization") String jwt) throws NoHandlerFoundException {

        UserEntity user = userService.findUserByJwt(jwt);

        List<ChatEntity> chat = this.chatService.findUsersChat(user);

        return new ResponseEntity<>(chat,HttpStatus.OK);

    }


}
