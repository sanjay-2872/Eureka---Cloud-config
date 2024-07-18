package com.npst.config.server.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseConstants {



    SUCCESS_OK("SUCCESS",  00),
    SUCCESS_CREATED("SUCCESS",  00),
    FAILURE("Failure",  01),
    BAD_REQUEST("BAD_REQUEST",  11);

    private final String respMsg;
    private final int respCode;

}
