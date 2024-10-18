package com.bmpworkflow.workflowtaskapi.mapper;

import com.bmpworkflow.workflowtaskapi.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/schema.sql")
class TaskMapperTest {

  @Autowired
  private TaskMapper taskMapper;

  @BeforeEach
  void setUp() {
    // テスト前に必要ならデータ初期化など
  }

  @Test
  void findAllTasks_shouldReturnTasks() {
    // Given: テストデータがある状態
    Task task1 = new Task();
    task1.setTaskId(1L);
    task1.setTitle("Task 1");
    task1.setDescription("Description 1");
    task1.setCompleted(0L);
    taskMapper.insertTask(task1);

    Task task2 = new Task();
    task2.setTaskId(2L);
    task2.setTitle("Task 2");
    task2.setDescription("Description 2");
    task2.setCompleted(1L);
    taskMapper.insertTask(task2);

    // When
    List<Task> tasks = taskMapper.findAllTasks();

    // Then
    assertThat(tasks).hasSize(2);
    assertThat(tasks.get(0).getTitle()).isEqualTo("Task 1");
    assertThat(tasks.get(1).getTitle()).isEqualTo("Task 2");
  }

  @Test
  void insertTask_shouldAddTaskToDatabase() {
    // Given
    Task task = new Task();
    task.setTaskId(3L);
    task.setTitle("New Task");
    task.setDescription("New Description");
    task.setCompleted(0L);

    // When
    taskMapper.insertTask(task);

    // Then
    List<Task> tasks = taskMapper.findAllTasks();
    assertThat(tasks).extracting(Task::getTitle).contains("New Task");
  }

  @Test
  void deleteTask_shouldRemoveTaskFromDatabase() {
    // Given
    Task task = new Task();
    task.setTaskId(4L);
    task.setTitle("Task to Delete");
    task.setDescription("Delete Description");
    task.setCompleted(0L);
    taskMapper.insertTask(task);

    // When
    taskMapper.deleteTask(4L);

    // Then
    List<Task> tasks = taskMapper.findAllTasks();
    assertThat(tasks).extracting(Task::getTaskId).doesNotContain(4L);
  }
}
