package com.socialmedia.api.service;

import com.socialmedia.api.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Service
public interface UserService {

    UserEntity registerUser(UserEntity user);

    UserEntity getUserByEmail(String email) throws NoHandlerFoundException;

    boolean isExistByEmail(String email );

    UserEntity followUser(Integer id1, Integer id2) throws NoHandlerFoundException;


    List<UserEntity> searchUser (String  query);

    List<UserEntity> getAllUser();

    UserEntity getUserByID(Integer id) throws NoHandlerFoundException;

    UserEntity updateUserById(Integer id , UserEntity userEntity) throws NoHandlerFoundException;

    String deleteUserById(Integer id) throws NoHandlerFoundException;

    UserEntity findUserByJwt(String jwt) throws NoHandlerFoundException;
}
