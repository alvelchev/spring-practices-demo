package com.springpageable.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "springdoc.swagger-ui")
public final class SwaggerProperties {

  private final String serverUrl;
  private final SwaggerUIData properties;

  @Getter
  @ConstructorBinding
  @AllArgsConstructor
  static class SwaggerUIData {
    private final String title;
    private final String description;
  }
}
