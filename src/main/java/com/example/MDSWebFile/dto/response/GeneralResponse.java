package com.example.MDSWebFile.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@ToString
@JsonInclude(value = NON_NULL, content = NON_EMPTY)
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse<T> {

    int code;
    String message;
    T data;


}

