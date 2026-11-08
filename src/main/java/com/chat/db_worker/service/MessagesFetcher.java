package com.chat.db_worker.service;

import com.chat.db_worker.model.MessageBody;
import com.chat.db_worker.repository.MessageBodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class MessagesFetcher {
    @Autowired
    private MessageBodyRepository messageBodyRepository;

    public List<String> messageBodyListOfUser(String userName)
    {
        return this.messageBodyRepository.findDistinctChatPartners(userName);
    }

    public List<MessageBody> findConversation(String userName, String targetUser, String cursor1)
    {

        Pageable pageable = PageRequest.of(0,15, Sort.by(Sort.Direction.DESC,"timestamp"));

        if(cursor1 == null)
        {
            System.out.println(cursor1);
            return this.messageBodyRepository.findConversation(userName,targetUser,pageable);
        }
        else {
                Instant cursor = Instant.parse(cursor1);
            return this.messageBodyRepository.findOlderMessages(userName,targetUser,cursor,pageable);
        }

    }
}
