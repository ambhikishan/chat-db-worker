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

    public List<MessageBody> messageBodyListOfUser(String userName)
    {
        return this.messageBodyRepository.findAllUserMessages(userName);
    }

    public List<MessageBody> findConversation(String userName, String targetUser)
    {
        return messageBodyRepository.findConversation(userName,targetUser);
    }
}
