package com.chat.db_worker.service;

import com.chat.db_worker.model.MessageBody;
import com.chat.db_worker.repository.MessageBodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesFetcher {
    @Autowired
    private MessageBodyRepository messageBodyRepository;

    public List<MessageBody> messageBodyList()
    {
        return this.messageBodyRepository.findAll();
    }
}
