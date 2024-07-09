package com.socialmedia.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @Column(name = "msg_Content")
    private String content;

    @Column(name = "msg_image")
    private String image;
    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @ManyToOne
    private UserEntity user;

    @JsonIgnore
    @ManyToOne
    private ChatEntity chat;
}
