package com.experimental.aservice.service.impl;

import com.experimental.aservice.dto.MsgDto;
import com.experimental.aservice.service.Sender;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderKafka implements Sender {

    private final Gson gson;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean send(MsgDto msgDto) {
        msgDto.setMsgBetweenServices(msgDto.getMsgBetweenServices() + " --> a-service here");
        log.info("Send msg to Service-a " + msgDto);
        return kafkaTemplate.send("api-gateway", gson.toJson(msgDto)).isDone();
    }


    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send("api-gateway", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
