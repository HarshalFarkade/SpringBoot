package com.socialmedia.api.controller;

import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/api/allUsers")
    public List<UserEntity> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/api/getUserById/{id}")
    public UserEntity getUserById(@PathVariable Integer id) throws NoHandlerFoundException {
        return userService.getUserByID(id);
    }

    @GetMapping("/api/getUserByEmail/{email}")
    public UserEntity getUserByEmail(@PathVariable String email)throws NoHandlerFoundException{
        return userService.getUserByEmail(email);
    }

    @PutMapping("/api/updateUser")
    public UserEntity updateUserById(@RequestHeader("Authorization") String jwt , @RequestBody UserEntity userEntity) throws NoHandlerFoundException {
        UserEntity user = userService.findUserByJwt(jwt);
        return userService.updateUserById(user.getId(), userEntity);
    }

    @DeleteMapping("/api/deleteById/{id}")
    public String deleteUserById(@PathVariable Integer id) throws NoHandlerFoundException {
         userService.deleteUserById(id);
         return "User Delete successfully";
    }

    @PutMapping("/api/users/follow/{id2}")
    public UserEntity  followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer id2) throws NoHandlerFoundException {
        UserEntity reqUser = userService.findUserByJwt(jwt);
        UserEntity user =  userService.followUser(reqUser.getId(), id2);
        return user;
    }

    @GetMapping("/api/users/search")
    public List<UserEntity>  searchUser(@RequestParam ("query") String query){
        List<UserEntity> user  = userService.searchUser(query);
        return user;
    }

    @GetMapping("/api/users/profile")
    public UserEntity getUserFromToken(@RequestHeader("Authorization") String jwt) throws NoHandlerFoundException {

        //  System.out.println("jwt--------"+jwt);
        UserEntity user =  userService.findUserByJwt(jwt);
        return user;
    }


}
