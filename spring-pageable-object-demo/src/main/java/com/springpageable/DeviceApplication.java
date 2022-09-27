package com.springpageable;

import com.springpageable.configuration.SwaggerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SwaggerProperties.class)
public class DeviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeviceApplication.class, args);
  }
}
