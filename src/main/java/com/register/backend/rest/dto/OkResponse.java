package com.register.backend.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.MDC;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class OkResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Reference")
    private String reference;

    @JsonProperty("Timestamp")
    @org.springframework.format.annotation.DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime timestamp;

    public OkResponse() {
        this.reference = MDC.get("LOGGING.ID");
        this.timestamp = OffsetDateTime.now();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
