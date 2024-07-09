package com.socialmedia.api.repository;

import com.socialmedia.api.entity.MessageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<MessageEntity,Integer> {

    @Transactional
    @Query("SELECT message FROM MessageEntity message WHERE message.chat.chatId = :chatId")
    List<MessageEntity> findByChatId(Integer chatId);
}
