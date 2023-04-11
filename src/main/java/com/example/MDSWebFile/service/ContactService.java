package com.example.MDSWebFile.service;

import com.example.MDSWebFile.config.ConfigProperties;
import com.example.MDSWebFile.config.Constant;
import com.example.MDSWebFile.dto.request.ContactRequest;
import com.example.MDSWebFile.dto.request.FindByEmailRequest;
import com.example.MDSWebFile.dto.request.FindByStatusRequest;
import com.example.MDSWebFile.dto.request.SendEmailRequest;
import com.example.MDSWebFile.dto.response.GeneralResponse;
import com.example.MDSWebFile.models.Contact;
import com.example.MDSWebFile.repo.ContactRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Collections;

@AllArgsConstructor
@Service
public class ContactService {
    private static final String EMAIL_URL = "http://196.223.153.235:8761/email/email-send";
    private final ContactRepo repo;
    final private ObjectMapper objectMapper;

    final private ConfigProperties properties;
    public GeneralResponse contact(ContactRequest request) throws JsonProcessingException {

        Contact contact = new Contact();
        BeanUtils.copyProperties(request, contact);

        SendEmailRequest emailRequest = new SendEmailRequest();

        emailRequest.setTo(Collections.singletonList(request.getEmail()));
        emailRequest.setSubject("Contacting MaxnetDS");
        emailRequest.setContent("Thank you for contacting MaxnetDS we will get back to you as soon as possible!");
        emailRequest.setEmailId(properties.getConfig());
        emailRequest.setCc("");
        GeneralResponse generalResponse = sendEmail(emailRequest);
        System.out.println(generalResponse);
        if (generalResponse.getCode() == Constant.ResponseCode.Success.code) {
            contact.setStatus("SUCCESS");
        } else {
            contact.setStatus("FAILED");
        }
        repo.save(contact);
        emailRequest.setTo(Collections.singletonList(properties.getConfig()));
        emailRequest.setSubject("Contacting MaxnetDS");
        emailRequest.setContent(request.getName() + "\n" + request.getEmail() + " sent: \n"
                + request.getMessage() + "\n email sent to them was: " + contact.getStatus());
        emailRequest.setEmailId(properties.getConfig());
        emailRequest.setCc("");
        sendEmail(emailRequest);
        return new GeneralResponse<>(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null);
    }

    private GeneralResponse sendEmail(SendEmailRequest emailRequest) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        RestTemplate restTemplate = new RestTemplate();

        MultipartFile file = null;
//        MultiValueMap<String, Object> bigMap= new LinkedMultiValueMap<String, Object>();
//        bigMap.add("file",null);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("data", emailRequest);
        map.add("file", file);
//        map.add("to", emailRequest.getTo());
//        map.add("cc", emailRequest.getCc());
//        map.add("email_id", emailRequest.getEmailId());
//        map.add("content", emailRequest.getContent());
//        map.add("subject", emailRequest.getSubject());
//        bigMap.add("data",map);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URI.create(EMAIL_URL), request, String.class);

        GeneralResponse response = objectMapper.readValue(responseEntity.getBody(), GeneralResponse.class);
        return response;
    }


    public GeneralResponse all(int page) {
//        Specification<Transaction> specs = filter.getSpecs(request);
        Pageable pageable = PageRequest.of(page - 1, 30);
        return new GeneralResponse<>(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, repo.findAll(pageable));
    }


    public GeneralResponse findByEmail(FindByEmailRequest request) {
//        Specification<Transaction> specs = filter.getSpecs(request);
        //  Pageable pageable = PageRequest.of(page - 1, 30);
        return new GeneralResponse<>(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, repo.findByEmail(request.getEmail()));
    }


    public GeneralResponse findByStatus(FindByStatusRequest request, int page) {
//        Specification<Transaction> specs = filter.getSpecs(request);
        Pageable pageable = PageRequest.of(page - 1, 30);
        return new GeneralResponse<>(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, repo.findByStatus(request.getStatus().toUpperCase(), pageable));
    }


}

