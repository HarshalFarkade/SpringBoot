package com.socialmedia.api.service;

import com.socialmedia.api.entity.ChatEntity;
import com.socialmedia.api.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Service
public interface ChatService {

    public ChatEntity createChat( UserEntity reqUser, UserEntity user2) throws NoHandlerFoundException;

    public ChatEntity findChatById(Integer chatId) throws NoHandlerFoundException;

    List<ChatEntity> findUsersChat(UserEntity user) ;
}
