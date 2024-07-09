package com.socialmedia.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private Integer id;

    @Column(name = "first_Name",nullable = false)
    private String firstName;

    @Column(name = "last_Name",nullable = false)
    private String lastName;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "gender",nullable = false)
    private String gender;

    private List<Integer> followers = new ArrayList<>();

    private List<Integer> following = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<PostEntity> savePost = new ArrayList<>();


}
