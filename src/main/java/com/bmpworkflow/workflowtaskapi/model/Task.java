package com.bmpworkflow.workflowtaskapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // ゲッター、セッター、toString、equals、hashCodeを自動生成
@NoArgsConstructor // 引数なしコンストラクタを生成
@AllArgsConstructor // 引数ありコンストラクタを生成
public class Task {
  private Long taskId; // タスクのID
  private String title; // タスクのタイトル
  private String description; // タスクの説明
  private Long completed; // タスクの完了状態
}
