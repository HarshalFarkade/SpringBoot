package com.socialmedia.api.repository;

import com.socialmedia.api.entity.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepo extends JpaRepository<StoryEntity,Integer> {

    List<StoryEntity> findByUserId(Integer userId);
}
