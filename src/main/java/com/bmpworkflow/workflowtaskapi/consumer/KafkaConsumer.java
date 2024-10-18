package com.bmpworkflow.workflowtaskapi.consumer;

import com.bmpworkflow.workflowtaskapi.model.TaskMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.micrometer.common.util.StringUtils;

/**
 * Kafkaメッセージを消費するクラスです。Kafkaの「task-events」トピックからメッセージを受信し、
 * 受信したメッセージを基にTask APIエンドポイントを呼び出します。
 */
@Component
public class KafkaConsumer {

  @Autowired
  private RestTemplate restTemplate;

  /**
   * Kafkaの「task-events」トピックからメッセージをリッスンします。
   * 
   * @param message 受信したメッセージ
   */
  @KafkaListener(topics = "task-events", groupId = "task-api-group")
  public void listen(String message) {
    System.out.println("Received message: " + message);

    // メッセージが空白の場合は処理を終了
    if (StringUtils.isBlank(message)) {
      System.out.println("Received empty message, ignoring it.");
      return;
    }

    // メッセージを処理し、APIエンドポイントを呼び出す
    callTaskApiEndpoint(message);
  }

  /**
   * 受信したメッセージをTaskMessageオブジェクトに変換し、対応するAPIエンドポイントを呼び出します。
   * 
   * @param message Kafkaから受信したメッセージ
   */
  private void callTaskApiEndpoint(String message) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      // メッセージをTaskMessageオブジェクトに変換
      TaskMessage taskMessage = objectMapper.readValue(message, TaskMessage.class);
      String apiUrl = "http://localhost:8080" + taskMessage.getApiEndpoint();

      // HTTPメソッドに応じた処理を行う
      switch (taskMessage.getHttpMethod()) {
        case "POST":
          restTemplate.postForEntity(apiUrl, taskMessage.getParams(), String.class);
          break;
        case "DELETE":
          restTemplate.delete(apiUrl + "/" + taskMessage.getParams().getTaskId());
          break;
        // 他のHTTPメソッドに対する処理を追加することもできます
      }
    } catch (JsonProcessingException e) {
      // JSON処理エラー時のログ出力
      e.printStackTrace();
    }
  }
}
