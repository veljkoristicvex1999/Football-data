package com.example.ConsumerApp;


import com.example.ConsumerApp.swagger.SwaggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication()
@ComponentScan({"com.example.ConsumerApp"})
@EnableAutoConfiguration
@EnableJpaRepositories("com.example.ConsumerApp.repository")
@EnableSwagger2
public class ConsumerAppApplication {

    private final SwaggerImpl swagger;

    @Autowired
    public ConsumerAppApplication(SwaggerImpl swagger) {
        this.swagger = swagger;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerAppApplication.class, args);
    }


}




