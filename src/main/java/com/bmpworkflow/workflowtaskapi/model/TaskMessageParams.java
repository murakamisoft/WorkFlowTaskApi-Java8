package com.bmpworkflow.workflowtaskapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskMessageParams {
  private Long taskId;
  private String title;
  private String description;
  private Long completed;
}
