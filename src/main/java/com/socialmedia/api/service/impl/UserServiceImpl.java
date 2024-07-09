package com.socialmedia.api.service.impl;

import com.socialmedia.api.config.JwtProvider;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.UserRepo;
import com.socialmedia.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registerUser(UserEntity user) {
        UserEntity newUser= new UserEntity();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());
        return  this.userRepo.save(newUser);
    }


    @Override
    public UserEntity getUserByEmail(String email) throws NoHandlerFoundException{
        try {
            boolean  user =  userRepo.existsByEmail(email);
            if(user){
                return  userRepo.getUserByEmail(email);
            }else {
                throw new NoHandlerFoundException("user with this email:"+email,"is not found",null);
            }
        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public boolean isExistByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserEntity followUser(Integer reqUserId, Integer id2) throws NoHandlerFoundException {

        UserEntity reqUser = getUserByID(reqUserId);

        UserEntity user2 =getUserByID(id2);

        user2.getFollowers().add(reqUserId);

        reqUser.getFollowing().add(id2);
        
        userRepo.save(reqUser);
        userRepo.save(user2);
        return reqUser;
    }

    @Override
    public List<UserEntity> searchUser(String query) {
        return userRepo.searchUser(query);
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepo.findAll();
    }


    @Override
    public UserEntity getUserByID(Integer id) throws NoHandlerFoundException{

        Optional<UserEntity> user = userRepo.findById(id);
        try{
            if(user.isPresent()){
                return user.get();
            }else {
                throw new NoHandlerFoundException("User with this id is :" + id," is not found", null);
            }

        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public UserEntity updateUserById(Integer id, UserEntity userEntity) throws NoHandlerFoundException{

        try{
            Optional<UserEntity> user = userRepo.findById(id);
            if(user.isPresent()){
                UserEntity user1 = user.get();
                user1.setFirstName(userEntity.getFirstName());
                user1.setLastName(userEntity.getLastName());
                return userRepo.save(user1);
            }else {
                throw new NoHandlerFoundException("User with this id :"+id,"is not found",null);
            }

        }catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public String deleteUserById(Integer id) throws NoHandlerFoundException {

        try{
            Optional<UserEntity> user = userRepo.findById(id);
            if(user.isPresent()){
                UserEntity userEntity = user.get();
                userRepo.deleteById(userEntity.getId());
                return "User Delete Successfully";
            }else {
                throw new NoHandlerFoundException("User with this id :"+id, "is not found",null);
            }
        }catch(Exception ex){
            throw  ex;
        }
    }

    @Override
    public UserEntity findUserByJwt(String jwt) throws NoHandlerFoundException {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        UserEntity user = userRepo.getUserByEmail(email);
        return user;
    }
}
