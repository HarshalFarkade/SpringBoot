package com.socialmedia.api.controller;

import com.socialmedia.api.entity.MessageEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.service.ChatService;
import com.socialmedia.api.service.MessageService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService service;

    @PostMapping("/api/message/chats/{chatId}")
    public ResponseEntity<MessageEntity> createMessage(@RequestBody MessageEntity message,
                                                       @RequestHeader("Authorization") String jwt,
                                                       @PathVariable Integer  chatId) throws NoHandlerFoundException {

        UserEntity user = userService.findUserByJwt(jwt);

        return new ResponseEntity<>(messageService.createMessage(user,chatId,message), HttpStatus.CREATED);
    }


    @GetMapping("/api/getMessageByChatId/{chatId}")
    public ResponseEntity<List<MessageEntity>> message(@RequestHeader("Authorization")String  jwt,
                                                       @PathVariable Integer chatId) throws NoHandlerFoundException {
        UserEntity user = userService.findUserByJwt(jwt);

        List<MessageEntity> messages = messageService.findChatsMessage(chatId);

        return new ResponseEntity<>(messages,HttpStatus.OK);

    }
}
