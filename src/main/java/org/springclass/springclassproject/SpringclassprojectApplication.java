package org.springclass.springclassproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SpringclassprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringclassprojectApplication.class, args);
    }

}
