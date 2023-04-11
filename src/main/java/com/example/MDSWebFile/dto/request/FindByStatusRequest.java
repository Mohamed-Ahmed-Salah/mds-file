package com.example.MDSWebFile.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class FindByStatusRequest {
   @NotNull
   @NotBlank
    private String status;
}
