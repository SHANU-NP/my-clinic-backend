package com.myclinic.backend.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class ErrorMessage {
    private String message;
    private int statusCode;
    private Instant timestamp;
    private String description;

    public ErrorMessage(int statusCode, Instant timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}
