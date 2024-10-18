package com.bmpworkflow.workflowtaskapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.bmpworkflow.workflowtaskapi.model.Task;
import com.bmpworkflow.workflowtaskapi.service.TaskService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

  @InjectMocks
  private TaskController taskController;

  @Mock
  private TaskService taskService;

  private Task task;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    task = new Task(1L, "Test Task", "This is a test task", 0L);
  }

  @Test
  void getTasks() {
    List<Task> tasks = new ArrayList<>();
    tasks.add(task);

    when(taskService.getAllTasks()).thenReturn(tasks);

    List<Task> result = taskController.getTasks();

    assertEquals(1, result.size());
    assertEquals("Test Task", result.get(0).getTitle());
  }

  @Test
  void createTask() {
    when(taskService.createTask(task)).thenReturn(task);

    Task result = taskController.createTask(task);

    assertEquals("Test Task", result.getTitle());
    verify(taskService).createTask(task);
  }

  @Test
  void deleteTask() {
    doNothing().when(taskService).deleteTask(task.getTaskId());

    ResponseEntity<Void> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
    taskController.deleteTask(task.getTaskId());

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    verify(taskService).deleteTask(task.getTaskId());
  }
}
