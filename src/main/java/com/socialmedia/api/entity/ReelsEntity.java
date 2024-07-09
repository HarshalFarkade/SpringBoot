package com.socialmedia.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reels")
public class ReelsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  reelsId;

    @Column(name = "title")
    private String title;

    @Column(name = "video")
    private  String video;

    @ManyToOne
    private UserEntity user;

}
