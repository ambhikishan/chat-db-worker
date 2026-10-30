package com.chat.db_worker.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaDatabaseConsumer {

    @KafkaListener(topics = "chat-db-topic", groupId = "chat-db-writers")
    public String consumeAndSaveBatchToDataBase(List<String> message)
    {
        System.out.println(message);

        return message.toString();
    }

}
