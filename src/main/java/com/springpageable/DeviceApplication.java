package com.springpageable;

import com.springpageable.configuration.SwaggerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.springpageable.repository"})
public class DeviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DeviceApplication.class, args);
  }
}
