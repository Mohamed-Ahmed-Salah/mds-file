package com.example.MDSWebFile.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
public class SendEmailRequest {
    @NotBlank(message = "receiver should not be empty or null")
    List<String> to;
    String cc;
    @NotBlank(message = "content should not be empty or null")
    String content;
    @NotBlank(message = "subject should not be empty or null")
    String subject;
    @NotNull(message = "email_id should not be empty or null")
    @JsonProperty("email_id")
    String emailId;
    boolean seperated=false;



}
