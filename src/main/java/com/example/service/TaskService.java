package com.example.service;

import com.example.entity.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(int id);
    List<Task> getAllTasks();
    Task getTaskById(int id);
}
