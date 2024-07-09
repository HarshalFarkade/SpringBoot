package com.socialmedia.api.repository;

import com.socialmedia.api.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    @Transactional
    @Query("select user from  UserEntity user WHERE user.email =:email")
    public UserEntity getUserByEmail(String email) throws NoHandlerFoundException;

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserEntity u WHERE u.email = :email")
    boolean existsByEmail(String email);

    @Query("select u from UserEntity u where u.firstName LIKE %:query%  OR u.lastName LIKE %:query% OR u.email LIKE %:query%" )
    public List<UserEntity> searchUser(@Param("query") String query);


}
