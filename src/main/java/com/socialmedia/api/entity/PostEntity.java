package com.socialmedia.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String caption;

    private String image;

    private String video;

    @ManyToOne
    private UserEntity userEntity;

    private LocalDateTime createAt;

    @ManyToMany
    private List<UserEntity> liked = new ArrayList<>();

    @OneToMany
    private List<CommentEntity> comments = new ArrayList<>();



}
