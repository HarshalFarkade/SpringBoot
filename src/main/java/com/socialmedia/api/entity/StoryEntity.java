package com.socialmedia.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "story")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storyId;

    @ManyToOne
    private UserEntity user ;

    @Column(name = "Image")
    private String image;

    @Column(name = "Caption")
    private String caption;

    @Column(name = "timeStamp")
    private LocalDateTime timeStamp;
}
