package com.example.send.sendmail.controller;

import com.example.send.sendmail.constant.KeyRabiter;
import com.example.send.sendmail.dto.EmailReciveListRequestForm;
import com.example.send.sendmail.dto.EmailReciveRequest;
import com.example.send.sendmail.service.EmailReciveServiceImpl;
import com.example.send.sendmail.service.EmailServiceImpl;
import com.example.send.sendmail.service.SendmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class FormEmailController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    EmailReciveServiceImpl emailReciveService;

    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("/form")
    public String showFormEmail(Model model) {
        return "/index";
    }

    @PostMapping("/register")
    public String post(Model model, @ModelAttribute EmailReciveListRequestForm emailListRequest) throws JsonProcessingException {
        boolean allSuccess = true;
        String inputEmails = emailListRequest.getNameEmails();
        String[] emailArray = inputEmails.split("\\s+");
        ArrayList<String> stringList = new ArrayList<>();

        for (String email : emailArray) {
            // TODO save emailReciveRequest in the database
            EmailReciveRequest emailReciveRequestAdd = new EmailReciveRequest();
            emailReciveRequestAdd.setBody(emailListRequest.getBody());
            emailReciveRequestAdd.setNameEmail(email);


            if(emailService.getByNameEmail(email)==null){
                stringList.add(email);
            }
            if (!emailReciveService.addProductRecive(emailReciveRequestAdd)) {
                allSuccess = false;
                continue;
            } else {
                ObjectMapper objectMapper = new ObjectMapper();
                String queuePayloadString = objectMapper.writeValueAsString(emailReciveRequestAdd);
                System.out.println(emailReciveRequestAdd.getId());
                rabbitTemplate.convertAndSend(KeyRabiter.QUEUE_NAME, queuePayloadString);
            }
        }

        if (allSuccess) {
            model.addAttribute("message", "All users registered successfully!");
            return "/index";
        } else {
            model.addAttribute("checkEmail", stringList+"User registration failed for one or more users!");
            return "/index";
        }

    }
}
