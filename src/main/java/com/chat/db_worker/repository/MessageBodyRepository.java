package com.chat.db_worker.repository;

import com.chat.db_worker.model.MessageBody;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageBodyRepository extends MongoRepository<MessageBody,String> {
}
