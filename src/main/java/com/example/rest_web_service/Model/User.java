package com.example.rest_web_service.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;




@Data
@Document(collection = "users")
public class User {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    private String email;
    private String password;
    private String fullName;

    public User(){}
    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }
}