package org.springclass.springclassproject.model;

import lombok.Data;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test.db")
@Data
public class DatabaseInfo {
    private String url;
    private String username;
    private String password;
}
