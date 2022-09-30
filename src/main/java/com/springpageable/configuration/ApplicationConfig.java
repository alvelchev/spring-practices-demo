package com.springpageable.configuration;

import com.springpageable.model.mapper.FutureDeviceMapper;
import com.springpageable.model.mapper.FutureDeviceMapperImpl;
import com.springpageable.model.mapper.UserMapper;
import com.springpageable.model.mapper.UserMapperImpl;
import com.springpageable.storage.CountryStorage;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnProperty(value = "springdoc.api-docs.enabled", havingValue = "true")
@ConfigurationPropertiesScan("com.device.configuration")
@AllArgsConstructor
public class ApplicationConfig {

  private final SwaggerProperties swaggerProperties;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(getInfo()).servers(getServers());
  }

  @Bean
  public FutureDeviceMapper futureDeviceMapper() {
    return new FutureDeviceMapperImpl();
  }
  @Bean
  public UserMapper userMapper() {
    return new UserMapperImpl();
  }

  @Bean
  public CountryStorage countryStorage() {
    return new CountryStorage();
  }

  private Info getInfo() {
    return new Info()
        .title(swaggerProperties.getProperties().getTitle())
        .description(swaggerProperties.getProperties().getDescription());
  }

  private List<Server> getServers() {
    return List.of(new Server().url(swaggerProperties.getServerUrl()));
  }
}
