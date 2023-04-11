package com.example.MDSWebFile.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContactRequest {
    @NotNull(message = "name should not be empty or null")
    @NotBlank(message = "name should not be empty or null")
    private String name;
    @NotNull(message = "email should not be empty or null")
    @NotBlank(message = "email should not be empty or null")
    private String email;
    @NotNull(message = "message should not be empty or null")
    @NotBlank(message = "message should not be empty or null")
    private String message;
}
