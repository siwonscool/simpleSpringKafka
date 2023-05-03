package com.example.simplespringkafka.service;

import com.example.simplespringkafka.dto.KafkaMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC_NAME = "topic5";
    private final KafkaTemplate<String, String> stringKafkaTemplate;
    private final KafkaTemplate<String, KafkaMessageDto> jsonKafkaTemplate;

    public void sendJson(KafkaMessageDto kafkaMessageDto){
        jsonKafkaTemplate.send(TOPIC_NAME, kafkaMessageDto);
    }

    public void sendString(String message){
        stringKafkaTemplate.send(TOPIC_NAME, message);
    }

    public void sendWithCallback(String message){
        ListenableFuture<SendResult<String,String>> future = stringKafkaTemplate.send(TOPIC_NAME, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.info("Failed {} due to : {}", message, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent : {}, Offset : {}", message, result.getRecordMetadata().offset());
            }
        });
    }
}
