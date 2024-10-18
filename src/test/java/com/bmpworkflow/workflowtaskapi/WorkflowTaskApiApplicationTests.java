package com.bmpworkflow.workflowtaskapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WorkflowTaskApiApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void contextLoads() {
    // アプリケーションコンテキストが正しくロードされていることを確認
    assertThat(applicationContext).isNotNull();
  }

  @Test
  void restTemplateBeanShouldBeDefined() {
    // RestTemplateのBeanが正しく定義されていることを確認
    RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
    assertThat(restTemplate).isNotNull();
  }

}
