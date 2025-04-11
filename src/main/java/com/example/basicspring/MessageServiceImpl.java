package com.example.basicspring;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Override
    public String processMessage(String message) {

        return message;
    }
}
