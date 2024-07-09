package com.socialmedia.api.controller;

import com.socialmedia.api.entity.ReelsEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.service.ReelService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ReelController {

    @Autowired
    private ReelService reelService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels/save")
    public ResponseEntity<ReelsEntity> createReels(@RequestBody  ReelsEntity reels, @RequestHeader("Authorization")String  jwt) throws NoHandlerFoundException {
    //  get user from jwt token
        UserEntity user=userService.findUserByJwt(jwt);

        ReelsEntity saveReels = reelService.createReels(reels, user.getId());
        return new ResponseEntity<>(saveReels, HttpStatus.CREATED);
    }

    @GetMapping("/api/allReels")
    public ResponseEntity<List<ReelsEntity>> getAllReels(){
        return new ResponseEntity<List<ReelsEntity>>(reelService.findAllReels(),HttpStatus.OK);
    }

    @GetMapping("/api/getReelsByUserId/{userId}")
    public ResponseEntity<List<ReelsEntity>> getReelsByUserId(@PathVariable Integer userId) throws NoHandlerFoundException {
        List<ReelsEntity> reels = reelService.findUsersReel(userId);
        return new ResponseEntity<>(reels,HttpStatus.OK);
    }

    @DeleteMapping("/api/deleteReels/{reelsId}")
    public String deleteReelsByUserId(@PathVariable Integer reelsId)  throws NoHandlerFoundException{
        String deleteReels = reelService.deleteReelsById(reelsId);

        new ResponseEntity<>(deleteReels,HttpStatus.OK);
        return "Reels Deleted Successfully";

    }
}
