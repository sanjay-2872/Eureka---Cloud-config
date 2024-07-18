package com.npst.config.server.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ResponseMessage {
    private int respCode;
    private String respMsg;
    private Object respData;
}
