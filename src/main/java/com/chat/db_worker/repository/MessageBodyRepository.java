package com.chat.db_worker.repository;

import com.chat.db_worker.model.MessageBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface MessageBodyRepository extends MongoRepository<MessageBody,String> {

    // Initial load: Fetch absolute newest messages
    @Query("{ $or: [ { 'from': ?0, 'to': ?1 }, { 'from': ?1, 'to': ?0 } ] }")
    List<MessageBody> findConversation(String userA, String userB, Pageable pageable);

    // Scroll load: Fetch messages strictly older than the provided timestamp
    @Query("{ $or: [ { 'from': ?0, 'to': ?1 }, { 'from': ?1, 'to': ?0 } ], 'timestamp': { $lt: ?2 } }")
    List<MessageBody> findOlderMessages(String userA, String userB, Instant cursor, Pageable pageable);

    @Aggregation(pipeline = {
            "{ $match: { $or: [ { 'from': ?0 }, { 'to': ?0 } ] } }",
            "{ $group: { _id: { $cond: { if: { $eq: [ '$from', ?0 ] }, then: '$to', else: '$from' } } } }"
    })
    List<String> findDistinctChatPartners(String myUsername);

}
