package com.springpageable.configuration;

import com.springpageable.model.mapper.FutureDeviceMapper;
import com.springpageable.model.mapper.FutureDeviceMapperImpl;
import com.springpageable.model.mapper.UserMapper;
import com.springpageable.model.mapper.UserMapperImpl;
import com.springpageable.storage.CountryStorage;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", havingValue = "true")
@ConfigurationPropertiesScan("com.device.configuration")
@AllArgsConstructor
public class ApplicationConfig {

  @Bean
  public FutureDeviceMapper futureDeviceMapper() {
    return new FutureDeviceMapperImpl();
  }
  @Bean
  public UserMapper userMapper() {
    return new UserMapperImpl();
  }

  @Bean
  public CountryStorage countryStorage() throws IOException {
    return new CountryStorage();
  }
}
