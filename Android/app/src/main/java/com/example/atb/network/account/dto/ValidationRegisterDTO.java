package com.example.atb.network.account.dto;

import lombok.Data;

@Data
public class ValidationRegisterDTO {
    private int status;
    private String title;
    private RegisterErrorDTO errors;
}
