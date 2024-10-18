package com.bmpworkflow.workflowtaskapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmpworkflow.workflowtaskapi.mapper.TaskMapper;
import com.bmpworkflow.workflowtaskapi.model.Task;

import java.util.List;

@Service // サービスクラスであることを示す
public class TaskService {

  @Autowired
  private TaskMapper taskMapper; // タスクマッパーのインジェクション

  // すべてのタスクを取得するメソッド
  public List<Task> getAllTasks() {
    return taskMapper.findAllTasks();
  }

  public Task createTask(Task task) {
    taskMapper.insertTask(task); // 新たにタスクをDBに追加するメソッドを呼び出す
    return task; // 追加されたタスクを返す
  }

  // タスクを削除するメソッド
  public void deleteTask(Long taskId) {
    taskMapper.deleteTask(taskId); // マッパーを使用してDBからタスクを削除
  }

}
