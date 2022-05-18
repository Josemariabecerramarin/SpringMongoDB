package com.example.rest_web_service.Model;

import com.example.rest_web_service.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

import java.util.Optional;

@Repository
public interface UserDao extends MongoRepository<User,Long> {
    



}
