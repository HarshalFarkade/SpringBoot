package com.socialmedia.api.service.impl;

import com.socialmedia.api.entity.ChatEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.ChatRepo;
import com.socialmedia.api.service.ChatService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChartServiceImpl implements ChatService {

    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserService userService;


    @Override
    public ChatEntity createChat( UserEntity reqUser, UserEntity user2) throws NoHandlerFoundException {
        // Retrieve UserEntity objects from their IDs

        ChatEntity isExist = chatRepo.findChatByUserId(reqUser, user2);
        if (isExist != null) {
            return isExist;
        } else {
            ChatEntity chatEntity = new ChatEntity();
            chatEntity.getUsers().add(user2);
            chatEntity.getUsers().add(reqUser);
            chatEntity.setTimeStamp(LocalDateTime.now());

            return chatRepo.save(chatEntity);
        }
    }


    @Override
    public ChatEntity findChatById(Integer chatId) throws NoHandlerFoundException {
        try {
            Optional<ChatEntity> chat = chatRepo.findById(chatId);
            if (chat.isPresent()) {
                return chatRepo.findById(chatId).get();
            } else {
                throw new NoHandlerFoundException("chat with this id is not found:" + chatId, null, null);
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<ChatEntity> findUsersChat(UserEntity user)  {
        return chatRepo.findByUsers(user);
    }
}
