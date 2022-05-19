package com.example.rest_web_service.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "banco")
public class Banco {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "banco_secuence";

    private String nombre;

    @DBRef
    List<User> users;

    public Banco(String nombre){
        this.nombre = nombre;
    }

    public Banco(){}
}
