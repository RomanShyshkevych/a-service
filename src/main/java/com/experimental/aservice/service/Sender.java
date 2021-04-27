package com.experimental.aservice.service;

import com.experimental.aservice.dto.MsgDto;

public interface Sender {
    boolean send(MsgDto msgDto);
}
