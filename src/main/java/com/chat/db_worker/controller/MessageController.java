package com.chat.db_worker.controller;

import com.chat.db_worker.service.MessagesFetcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessagesFetcher messagesFetcher;

    @GetMapping("/")
    public ResponseEntity<?> messages(HttpServletRequest request)
    {      String userName = (String) request.getAttribute("extractedUsername");
        return ResponseEntity.ok(this.messagesFetcher.messageBodyListOfUser(userName));
    }

    @GetMapping("/conversation/{to}")
    public ResponseEntity<?> findConversation(@PathVariable("to") String targetUser, HttpServletRequest request)
    {
        String userName = (String) request.getAttribute("extractedUsername");
        return ResponseEntity.ok(this.messagesFetcher.findConversation(userName,targetUser));
    }
}
