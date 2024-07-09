package com.socialmedia.api.service;

import com.socialmedia.api.entity.ReelsEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Service
public interface ReelService {

    ReelsEntity createReels(ReelsEntity reel ,Integer userId) throws NoHandlerFoundException;

    List<ReelsEntity> findAllReels();

    List<ReelsEntity> findUsersReel(Integer userId) throws NoHandlerFoundException;

    String deleteReelsById(Integer  reelsId) throws NoHandlerFoundException;

}
