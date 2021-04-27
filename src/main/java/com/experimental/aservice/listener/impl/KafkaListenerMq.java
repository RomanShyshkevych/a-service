package com.experimental.aservice.listener.impl;

import com.experimental.aservice.dto.MsgDto;
import com.experimental.aservice.listener.MqListener;
import com.experimental.aservice.service.Sender;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
@EnableKafka
public class KafkaListenerMq implements MqListener {

    private final Gson gson;

    private final Sender sender;

    @Override
    @KafkaListener(topics = "a-service")
    public void onMessage(String msg) {
        log.info(msg);
        sender.send(gson.fromJson(msg, MsgDto.class));
    }
}
