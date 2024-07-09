package com.socialmedia.api.controller;

import com.socialmedia.api.config.JwtProvider;
import com.socialmedia.api.entity.UserEntity;
import com.socialmedia.api.repository.UserRepo;
import com.socialmedia.api.reponse.AuthResponse;
import com.socialmedia.api.request.LoginRequest;
import com.socialmedia.api.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signUp")
    public AuthResponse saveUser(@RequestBody UserEntity userEntity) throws Exception {
        // Check if the email already exists in the database
        UserEntity existingUser = userRepo.getUserByEmail(userEntity.getEmail());
        if (existingUser != null) {
            throw new Exception("Email already exists: " + userEntity.getEmail());
        }

        // Create a new user entity
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userEntity.getFirstName());
        newUser.setLastName(userEntity.getLastName());
        newUser.setEmail(userEntity.getEmail());
        newUser.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        newUser.setGender(userEntity.getGender());

        // Save the new user
        UserEntity savedUser = userRepo.save(newUser);

        // Generate authentication token for the newly registered user
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);

        // Create authentication response
        AuthResponse response = new AuthResponse(token, "Registered successfully");
        return response;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/signin")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse response = new AuthResponse(token,"Login Successfully");
        return response ;
    }



    private Authentication authenticate(String email, String password) {

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid UserName");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw  new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }


}
