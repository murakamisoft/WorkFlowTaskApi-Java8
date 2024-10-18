package com.bmpworkflow.workflowtaskapi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bmpworkflow.workflowtaskapi.model.Task;

@Mapper
public interface TaskMapper {

  @Select("SELECT * FROM m_task")
  List<Task> findAllTasks();

  @Insert("INSERT INTO m_task (task_id, title, description, completed) VALUES (#{taskId}, #{title}, #{description}, #{completed})")
  void insertTask(Task task);

  @Delete("DELETE FROM m_task WHERE task_id = #{taskId}")
  void deleteTask(Long taskId);
}
