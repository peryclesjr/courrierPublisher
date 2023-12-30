package com.courrier.courrierpublish.controller;

import com.courrier.courrierpublish.service.impl.MessagePuublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    MessagePuublisherService publisherService;

    public MessageController(MessagePuublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/publish")
    public String sendToCreatedDelivery(@RequestBody String message) {
        publisherService.publishMessage(message);
        return "Mensagem enviada para createdDelivery";
    }

}
