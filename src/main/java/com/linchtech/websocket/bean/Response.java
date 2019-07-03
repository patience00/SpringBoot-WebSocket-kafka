package com.linchtech.websocket.bean;

/**
 * comment
 *
 * @author: 通天晓
 * @review:
 * @date: 2018-08-29 17:26
 * @version: 1.0
 */
public class Response {
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    private String responseMessage;
    public Response(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}