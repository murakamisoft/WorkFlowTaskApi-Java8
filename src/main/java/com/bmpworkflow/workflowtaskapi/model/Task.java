package com.bmpworkflow.workflowtaskapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
  private Long taskId; // タスクのID
  private String title; // タスクのタイトル
  private String description; // タスクの説明
  private Long completed; // タスクの完了状態
}
