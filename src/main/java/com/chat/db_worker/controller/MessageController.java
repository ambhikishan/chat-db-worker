package com.chat.db_worker.controller;

import com.chat.db_worker.service.MessagesFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessagesFetcher messagesFetcher;

    @GetMapping("/")
    public ResponseEntity<?> messages()
    {
        return ResponseEntity.ok(this.messagesFetcher.messageBodyList());
    }
}
