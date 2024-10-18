package com.bmpworkflow.workflowtaskapi.consumer;

import com.bmpworkflow.workflowtaskapi.model.TaskMessage;
import com.bmpworkflow.workflowtaskapi.model.TaskMessageParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.*;

class KafkaConsumerTest {

  @Mock
  private RestTemplate restTemplate;

  @InjectMocks
  private KafkaConsumer kafkaConsumer;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    objectMapper = new ObjectMapper();
  }

  @Test
  void listen_withEmptyMessage_shouldNotCallApiEndpoint() {
    // Given
    String emptyMessage = "";

    // When
    kafkaConsumer.listen(emptyMessage);

    // Then
    // APIエンドポイントを呼び出していないことを確認
    verify(restTemplate, never()).postForEntity(anyString(), any(), any());
    verify(restTemplate, never()).delete(anyString());
  }

  @Test
  void listen_withValidMessage_shouldCallPostApiEndpoint() throws JsonProcessingException {
    // Given
    TaskMessage taskMessage = new TaskMessage();
    taskMessage.setHttpMethod("POST");
    taskMessage.setApiEndpoint("/tasks");
    taskMessage.setParams(new TaskMessageParams(null, "123", "Test task", 0L));

    String validMessage = objectMapper.writeValueAsString(taskMessage);

    // When
    kafkaConsumer.listen(validMessage);

    // Then
    // POSTエンドポイントが呼び出されたことを確認
    verify(restTemplate, times(1)).postForEntity(eq("http://localhost:8080/tasks"), eq(taskMessage.getParams()),
        eq(String.class));
  }

  @Test
  void listen_withValidMessage_shouldCallDeleteApiEndpoint() throws JsonProcessingException {
    // Given
    TaskMessage taskMessage = new TaskMessage();
    taskMessage.setHttpMethod("DELETE");
    taskMessage.setApiEndpoint("/tasks");
    TaskMessageParams params = new TaskMessageParams(1L, "123", "Test task", 0L);
    taskMessage.setParams(params);

    String validMessage = objectMapper.writeValueAsString(taskMessage);

    // When
    kafkaConsumer.listen(validMessage);

    // Then
    // DELETEエンドポイントが呼び出されたことを確認
    verify(restTemplate, times(1)).delete(eq("http://localhost:8080/tasks/1"));
  }

  @Test
  void listen_withInvalidJson_shouldHandleJsonProcessingException() throws JsonProcessingException {
    // Given
    String invalidJson = "invalid json";

    // When
    kafkaConsumer.listen(invalidJson);

    // Then
    // エンドポイントが呼び出されていないことを確認
    verify(restTemplate, never()).postForEntity(anyString(), any(), any());
    verify(restTemplate, never()).delete(anyString());
  }
}
