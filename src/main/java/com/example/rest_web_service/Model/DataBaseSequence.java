package com.example.rest_web_service.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "database_sequence")
public class DataBaseSequence {
    @Id
    private String id;

    private Long seq;

    DataBaseSequence(){

    }

}
