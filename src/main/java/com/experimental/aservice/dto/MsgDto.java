package com.experimental.aservice.dto;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MsgDto {
    private UUID activityId;
    private String msgBetweenServices;
}
