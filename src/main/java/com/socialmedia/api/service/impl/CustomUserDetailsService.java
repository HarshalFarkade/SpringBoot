package com.socialmedia.api.service.impl;

import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.UserRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       UserEntity user = userRepo.getUserByEmail(username);
       if (user == null){
           throw new  UsernameNotFoundException("user not found with email"+  username);
       }
       List<GrantedAuthority>  authorities = new ArrayList<>();

       return  new User(user.getEmail(),user.getPassword(),authorities);
    }

}
