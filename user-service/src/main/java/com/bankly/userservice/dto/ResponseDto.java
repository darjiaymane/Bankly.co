package com.bankly.userservice.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {
    private String status;

    private String message;

    private Object token;

    public ResponseDto(String status, String message, Object token) {
        this.status = status;
        this.message = message;
        this.token = token;
    }

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return token;
    }

    public void setData(Object token) {
        this.token = token;
    }
}
