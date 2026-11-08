package com.chat.db_worker.service;


import com.chat.db_worker.model.MessageBody;
import com.chat.db_worker.repository.MessageBodyRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaDatabaseConsumer {

   private MessageBodyRepository messageBodyRepository;
    private ObjectMapper objectMapper;


    public KafkaDatabaseConsumer(ObjectMapper objectMapper, MessageBodyRepository messageBodyRepository)
    {
        this.messageBodyRepository = messageBodyRepository;
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "chat-db-topic", groupId = "chat-db-writers-v2")
    public void consumeAndSaveBatchToDataBase(List<ConsumerRecord<String,String>> recordsList)
    {
        List<MessageBody> list = new ArrayList<>();

        for(ConsumerRecord<String,String> record : recordsList )
        {

            MessageBody messageBody = new MessageBody();
            String key[] = record.key().split("@");
            messageBody.setFrom(key[0]);
            messageBody.setTo(key[1]);
            messageBody.setMessage(record.value().substring(25+record.key().length(),record.value().length()-2));
            messageBody.setTimestamp();
            list.add(messageBody);

        }

    this.messageBodyRepository.saveAll(list);

    }



}
