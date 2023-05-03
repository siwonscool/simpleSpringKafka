package com.example.simplespringkafka.dto;

import lombok.Data;

@Data
public class KafkaMessageDto {
    private String name;
    private String message;
}
