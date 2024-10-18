package com.bmpworkflow.workflowtaskapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // ゲッター、セッター、toString、equals、hashCodeを自動生成
@NoArgsConstructor // 引数なしコンストラクタを生成
@AllArgsConstructor // 引数ありコンストラクタを生成
public class TaskMessageParams {
  private Long taskId;
  private String title;
  private String description;
  private Long completed;
}
