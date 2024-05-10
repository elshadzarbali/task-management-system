package com.example.service.impl;

import com.example.entity.TaskCategory;
import com.example.repository.TaskCategoryRepository;
import com.example.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskCategoryService")
public class TaskCategoryServiceImpl implements TaskCategoryService {
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Override
    public TaskCategory getTaskCategory(int id) {
        return taskCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<TaskCategory> getAllTaskCategories() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public void saveTaskCategory(TaskCategory taskCategory) {
        taskCategoryRepository.save(taskCategory);
    }

    @Override
    public void deleteTaskCategory(int id) {
        taskCategoryRepository.deleteById(id);
    }

    @Override
    public TaskCategory updateTaskCategory(TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }
}
