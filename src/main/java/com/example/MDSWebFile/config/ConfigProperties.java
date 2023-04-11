package com.example.MDSWebFile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "email")
@ConfigurationPropertiesScan
@Data
public class ConfigProperties {
    private String config;

}
