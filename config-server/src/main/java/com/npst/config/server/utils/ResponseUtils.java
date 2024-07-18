package com.npst.config.server.utils;


import com.npst.config.server.constants.ResponseConstants;
import com.npst.config.server.dto.ResponseMessage;

public class ResponseUtils {

    private ResponseUtils(){
    }

    public static ResponseMessage successResponseMsg(ResponseConstants responseConstants, Object respMsg){
        return ResponseMessage.builder()
                .respCode(responseConstants.getRespCode())
                .respMsg(responseConstants.getRespMsg())
                .respData(respMsg)
                .build();
    }
}
