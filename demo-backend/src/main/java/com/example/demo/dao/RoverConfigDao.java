package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.RoverConfig;
import com.example.demo.domain.User;

@Repository
public interface RoverConfigDao extends MongoRepository<RoverConfig, String> {

    List<RoverConfig> findByActive(boolean active);
}
