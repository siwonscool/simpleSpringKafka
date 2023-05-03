package com.example.simplespringkafka.controller;

import com.example.simplespringkafka.dto.KafkaMessageDto;
import com.example.simplespringkafka.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {
    private final KafkaProducerService kafkaProducerService;
    @RequestMapping("/publish")
    public String publishString(String message){
        kafkaProducerService.sendString(message);
        return "published a message : " + message;
    }

    @RequestMapping("/publish2")
    public String publishStringWithCallback(String message){
        kafkaProducerService.sendWithCallback(message);
        return "published a message with callback : " + message;
    }

    @RequestMapping("/publish3")
    public String publishJson(KafkaMessageDto kafkaMessageDto){
        kafkaProducerService.sendJson(kafkaMessageDto);
        return "published a message with callback : " + kafkaMessageDto.getName() + "," + kafkaMessageDto.getMessage();
    }
}
