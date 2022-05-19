package com.example.rest_web_service.Repository;

import com.example.rest_web_service.Model.Banco;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoDao extends MongoRepository<Banco, Long> {
}
