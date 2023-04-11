package com.example.MDSWebFile.config;
//
//import com.email.MaxnetEmailService.mapper.EmailConfigMapper;
//import com.email.MaxnetEmailService.mapper.EmailMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.client.RestTemplate;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanCreation {
//
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    @ConfigurationProperties("email")
//    public ConfigProperties configProperties(){
//        return new ConfigProperties();
//    }
//

}
