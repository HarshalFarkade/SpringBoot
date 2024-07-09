package com.socialmedia.api.service.impl;

import com.socialmedia.api.entity.ChatEntity;
import com.socialmedia.api.entity.MessageEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.ChatRepo;
import com.socialmedia.api.repository.MessageRepo;
import com.socialmedia.api.service.ChatService;
import com.socialmedia.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private  ChatService chatService;

    @Autowired
    private ChatRepo chatRepo;

    @Override
    public MessageEntity createMessage(UserEntity user, Integer chatId, MessageEntity message) throws NoHandlerFoundException {

        ChatEntity chat = chatService.findChatById(chatId);

        MessageEntity createNewMessage = new MessageEntity();

        createNewMessage.setChat(chat);
        createNewMessage.setContent(message.getContent());
        createNewMessage.setImage(message.getImage());
        createNewMessage.setTimeStamp(LocalDateTime.now());
        createNewMessage.setUser(user);

        MessageEntity saveMessage =  messageRepo.save(createNewMessage);
        chat.getMessage().add(saveMessage);
        chatRepo.save(chat);
        return saveMessage;
    }

    @Override
    public List<MessageEntity> findChatsMessage(Integer chatId) throws NoHandlerFoundException {

        ChatEntity chat = chatService.findChatById(chatId);


        return messageRepo.findByChatId(chatId);
    }
}
