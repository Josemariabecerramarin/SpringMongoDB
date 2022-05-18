package com.example.rest_web_service.Service;

import com.example.rest_web_service.Model.DataBaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SecuenceGenerator {

    private MongoOperations mongoOperations;

    @Autowired
    public SecuenceGenerator(MongoOperations mO){
        mongoOperations = mO;
    }

    public long generateSequence(String nSeq){
        DataBaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(nSeq)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DataBaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
