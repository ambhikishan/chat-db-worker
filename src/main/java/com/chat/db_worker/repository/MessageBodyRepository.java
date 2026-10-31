package com.chat.db_worker.repository;

import com.chat.db_worker.model.MessageBody;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageBodyRepository extends MongoRepository<MessageBody,String> {

    @Query("{ $or: [ { 'from': ?0 }, { 'to': ?0 } ] }")
    List<MessageBody> findAllUserMessages(String username);

    @Query("{ $or: [ { 'from': ?0, 'to': ?1 }, { 'from': ?1, 'to': ?0 } ] }")
    List<MessageBody> findConversation(String userA, String userB);
}
