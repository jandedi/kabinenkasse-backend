package com.register.backend.rest.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.register.backend.rest.dto.OkResponse;

@JsonPropertyOrder(alphabetic = true)
public class ErrorResponse extends OkResponse {

    @JsonProperty("ErrorCode")
    private String errorCode;

    @JsonProperty("Message")
    private String message;

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
