package com.socialmedia.api.repository;

import com.socialmedia.api.entity.ChatEntity;
import com.socialmedia.api.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<ChatEntity,Integer> {

    @Transactional
    @Query("SELECT chat FROM ChatEntity chat WHERE :user MEMBER OF chat.users")
    List<ChatEntity>  findByUsers( @Param("user") UserEntity user);

    @Transactional
    @Query("SELECT chat FROM ChatEntity chat WHERE :user2 MEMBER OF chat.users AND :reqUser MEMBER OF chat.users")
    ChatEntity findChatByUserId(@Param("user2") UserEntity user2,
                                @Param("reqUser") UserEntity reqUser);


}
