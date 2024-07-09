package com.socialmedia.api.repository;

import com.socialmedia.api.entity.ReelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelsRepo extends JpaRepository<ReelsEntity,Integer> {


    List<ReelsEntity> findByUserId(Integer userId);


}
