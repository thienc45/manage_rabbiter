package com.example.receive.receiverabiitmq.listeners;

import com.example.receive.receiverabiitmq.dto.EmaiReciveDto;
import com.example.receive.receiverabiitmq.entity.Email;
import com.example.receive.receiverabiitmq.entity.EmailRecive;
import com.example.receive.receiverabiitmq.repository.EmailReciveRepository;
import com.example.receive.receiverabiitmq.repository.EmailRepository;
import com.example.receive.receiverabiitmq.service.SendmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRegisteredListener {

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    EmailReciveRepository emailReciveRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    SendmailService sendmailService;

    public void onMessageReceived(String jsonMessage) {
        try {
            System.out.println(jsonMessage);
            EmaiReciveDto messageData = objectMapper.readValue(jsonMessage, EmaiReciveDto.class);

            System.out.println(messageData.toString());

            String body = messageData.getBody();
            List<String> nameEmails = new ArrayList<>();
            nameEmails.add(messageData.getNameEmail());

            System.out.println("Received body: " + messageData.getBody());
            System.out.println("Received nameEmail: " + messageData.getNameEmail());

            EmailRecive emailRecive = emailReciveRepository.findByEmail_NameAndStateAndId(messageData.getNameEmail(), 0, messageData.getId());
            if (emailRecive != null) {
                emailRecive.setState(1);
                emailReciveRepository.save(emailRecive);
            }
//                sendmailService.sendEmail(nameEmail, body);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
