package com.bmpworkflow.workflowtaskapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // 全てのエンドポイントに対してCORSを許可
        .allowedOrigins("http://localhost:3000") // 許可するオリジンを指定
        .allowedMethods("GET", "POST", "DELETE", "OPTIONS") // 許可するHTTPメソッドを指定
        .allowedHeaders("*") // 許可するヘッダー
        .allowCredentials(true); // 認証情報の送信を許可
  }
}
