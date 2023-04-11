package com.example.MDSWebFile.controller;

import com.example.MDSWebFile.dto.request.ContactRequest;
import com.example.MDSWebFile.dto.request.FindByEmailRequest;
import com.example.MDSWebFile.dto.request.FindByStatusRequest;
import com.example.MDSWebFile.dto.response.GeneralResponse;
import com.example.MDSWebFile.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping
@RestController
@CrossOrigin()
@RequiredArgsConstructor
public class ContactController {
private final ContactService service;

    @PostMapping("/contact-us")
    public ResponseEntity<GeneralResponse> contact(@RequestBody @Validated ContactRequest request) throws IOException {
        return new ResponseEntity<>(service.contact(request), HttpStatus.OK);
    }


    @GetMapping("/contact-us/page={page}")
    public ResponseEntity<GeneralResponse> all(@PathVariable int page) throws IOException {
        return new ResponseEntity<>(service.all(page), HttpStatus.OK);
    }

    @GetMapping("/contact-us/email")
    public ResponseEntity<GeneralResponse> findByEmail(@RequestBody @Validated FindByEmailRequest request) throws IOException {
        return new ResponseEntity<>(service.findByEmail(request), HttpStatus.OK);
    }


    @GetMapping("/contact-us/status/page={page}")
    public ResponseEntity<GeneralResponse> findByStatus(@RequestBody @Validated FindByStatusRequest request, @PathVariable int page) throws IOException {
        return new ResponseEntity<>(service.findByStatus(request,page), HttpStatus.OK);
    }


}
