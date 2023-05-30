package com.example.email.model;

import lombok.Data;

@Data
public class EmailModel {
    private String from;
    private String to;
    private String subject;
    private String message;
}
