package com.example.service;

import com.example.entity.TaskCategory;

import java.util.List;

public interface TaskCategoryService {
    TaskCategory getTaskCategory(int id);
    List<TaskCategory> getAllTaskCategories();
    void saveTaskCategory(TaskCategory taskCategory);
    void deleteTaskCategory(int id);
    TaskCategory updateTaskCategory(TaskCategory taskCategory);
}
