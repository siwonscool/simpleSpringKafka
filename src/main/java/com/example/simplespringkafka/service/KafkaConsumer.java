package com.example.simplespringkafka.service;

import com.example.simplespringkafka.dto.KafkaMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {
    private static final String TOPIC_NAME = "topic5";

    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = TOPIC_NAME)
    private void listenMessage(String jsonMapper){
        try {
            KafkaMessageDto message = objectMapper.readValue(jsonMapper, KafkaMessageDto.class);
            log.info(">>> {}, {}",message.getName(), message.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
