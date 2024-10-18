package com.bmpworkflow.workflowtaskapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bmpworkflow.workflowtaskapi.mapper.TaskMapper;
import com.bmpworkflow.workflowtaskapi.model.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

  @InjectMocks
  private TaskService taskService;

  @Mock
  private TaskMapper taskMapper;

  private Task task;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    task = new Task(1L, "Test Task", "This is a test task", 0L);
  }

  @Test
  void getAllTasks() {
    List<Task> tasks = new ArrayList<>();
    tasks.add(task);

    when(taskMapper.findAllTasks()).thenReturn(tasks);

    List<Task> result = taskService.getAllTasks();

    assertEquals(1, result.size());
    assertEquals("Test Task", result.get(0).getTitle());
  }

  @Test
  void createTask() {
    taskService.createTask(task);

    ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
    verify(taskMapper).insertTask(taskCaptor.capture());

    assertEquals(task.getTitle(), taskCaptor.getValue().getTitle());
  }

  @Test
  void deleteTask() {
    taskService.deleteTask(task.getTaskId());
    verify(taskMapper).deleteTask(task.getTaskId());
  }
}
