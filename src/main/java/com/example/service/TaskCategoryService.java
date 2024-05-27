package com.example.service;

import com.example.entity.TaskCategory;

import java.util.List;

public interface TaskCategoryService {
    List<TaskCategory> getAllTaskCategories();
    TaskCategory getTaskCategoryById(Integer id);
    TaskCategory createTaskCategory(TaskCategory taskCategory);
    TaskCategory updateTaskCategory(Integer id, TaskCategory taskCategory);
    void deleteTaskCategory(Integer id);
}
