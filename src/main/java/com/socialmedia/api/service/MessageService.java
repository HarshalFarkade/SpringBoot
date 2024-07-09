package com.socialmedia.api.service;

import com.socialmedia.api.entity.ChatEntity;
import com.socialmedia.api.entity.MessageEntity;
import com.socialmedia.api.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Service
public interface MessageService {

    MessageEntity  createMessage(UserEntity user, Integer chatId,  MessageEntity message) throws NoHandlerFoundException;

     List<MessageEntity> findChatsMessage(Integer chatId) throws NoHandlerFoundException;


}
