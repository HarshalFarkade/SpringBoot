package com.socialmedia.api.service.impl;

import com.socialmedia.api.entity.ReelsEntity;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.ReelsRepo;
import com.socialmedia.api.service.ReelService;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class ReelServiceImpl implements ReelService {

    @Autowired
    private ReelsRepo reelsRepo;

    @Autowired
    private UserService userService;

    @Override
    public ReelsEntity createReels(ReelsEntity reel, Integer userId) throws NoHandlerFoundException {

        try {
            UserEntity user =userService.getUserByID(userId);
            if(user != null) {
                ReelsEntity createReels = new ReelsEntity();
                createReels.setTitle(reel.getTitle());
                createReels.setVideo(reel.getVideo());
                createReels.setUser(user);
                return reelsRepo.save(createReels);
            }else {
                throw new NoHandlerFoundException("User with this id is not found :"+userId,null,null);
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public List<ReelsEntity> findAllReels() {
        return reelsRepo.findAll();
    }

    @Override
    public List<ReelsEntity> findUsersReel(Integer userId) throws NoHandlerFoundException {
        try {
            UserEntity user = userService.getUserByID(userId);
            if (user != null) {
                return reelsRepo.findByUserId(userId);
            } else {
                throw new NoHandlerFoundException("User with this id is not found:" + userId, null, null);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public String deleteReelsById(Integer reelsId) throws NoHandlerFoundException {
        try{
            Optional<ReelsEntity> reels=reelsRepo.findById(reelsId);
            if(reels.isPresent()){
                reelsRepo.deleteById(reelsId);
                return "Reels Deleted Successfully";
            }else {
                throw new NoHandlerFoundException("User with this id is not found:"+reelsId,null,null);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

}
