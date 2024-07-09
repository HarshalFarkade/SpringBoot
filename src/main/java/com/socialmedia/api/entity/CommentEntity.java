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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;


    @Column(name = "content")
    private String content;

    @ManyToOne
    private UserEntity user ;

    @ManyToMany
    private List<UserEntity> like =  new ArrayList<>();

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
