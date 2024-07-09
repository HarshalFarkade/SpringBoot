package com.socialmedia.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Chat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @Column(name = "chat_Name")
    private String  chatName ;

    @Column(name = "chat_image")
    private String image;

    @ManyToMany
    @Column(name = "users")
    private List<UserEntity> users = new ArrayList<>();

    @Column(name = "timeStamp")
    private LocalDateTime timeStamp;

    @OneToMany(mappedBy = "chat")
    private List<MessageEntity> message =new ArrayList<>();

}
