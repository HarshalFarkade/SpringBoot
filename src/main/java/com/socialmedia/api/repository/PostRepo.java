package com.socialmedia.api.repository;

import com.socialmedia.api.entity.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostEntity,Integer> {

    @Transactional
    @Query("select post from PostEntity post where post.userEntity.id = :userId")
    List<PostEntity> findPostByUserId(Integer userId);
}
