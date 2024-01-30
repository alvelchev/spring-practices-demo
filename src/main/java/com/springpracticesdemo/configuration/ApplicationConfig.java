package com.springpracticesdemo.configuration;

import com.springpracticesdemo.model.mapper.FutureDeviceMapper;
import com.springpracticesdemo.model.mapper.FutureDeviceMapperImpl;
import com.springpracticesdemo.model.mapper.UserMapper;
import com.springpracticesdemo.model.mapper.UserMapperImpl;
import com.springpracticesdemo.storage.CountryStorage;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", havingValue = "true")
@ConfigurationPropertiesScan("com.springpracticesdemo.configuration")
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
