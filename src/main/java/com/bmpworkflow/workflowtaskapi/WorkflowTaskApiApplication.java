package com.bmpworkflow.workflowtaskapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * WorkflowTaskApiアプリケーションのエントリーポイントクラスです。
 * Spring Bootアプリケーションを起動し、MyBatisのMapperスキャンを行います。
 */
@SpringBootApplication
@MapperScan("com.bmpworkflow.workflowtaskapi.mapper")
public class WorkflowTaskApiApplication {

  /**
   * メインメソッド。Spring Bootアプリケーションを起動します。
   *
   * @param args コマンドライン引数
   */
  public static void main(String[] args) {
    SpringApplication.run(WorkflowTaskApiApplication.class, args);
  }

  /**
   * RestTemplateのBean定義です。このBeanを他のクラスでDI（依存性注入）して使用できます。
   *
   * @return 新しいRestTemplateインスタンス
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
