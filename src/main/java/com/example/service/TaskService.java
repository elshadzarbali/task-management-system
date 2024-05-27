package com.example.service;

import com.example.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(int id);
    Task createTask(Task task);
    Task updateTask(Integer id, Task task);
    void deleteTask(int id);
}
