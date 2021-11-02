package com.ldh.ecommerce.response;

import org.springframework.http.HttpStatus;

public class CommonResponse {
        public HttpStatus httpStatus;
        public MessageResponse messageResponse;
        public Object data;

    public CommonResponse(HttpStatus httpStatus, MessageResponse messageResponse, Object data) {
        this.httpStatus = httpStatus;
        this.messageResponse = messageResponse;
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
