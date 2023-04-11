package com.example.MDSWebFile.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FindByEmailRequest {
    @NotBlank
    @NotNull
    private String email;
}
