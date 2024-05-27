package com.example.service.impl;

import com.example.entity.TaskCategory;
import com.example.repository.TaskCategoryRepository;
import com.example.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service("taskCategoryService")
public class TaskCategoryServiceImpl implements TaskCategoryService {
    private final TaskCategoryRepository taskCategoryRepository;

    public TaskCategoryServiceImpl(TaskCategoryRepository taskCategoryRepository) {
        this.taskCategoryRepository = taskCategoryRepository;
    }

    @Override
    public List<TaskCategory> getAllTaskCategories() {
        return taskCategoryRepository.findAll();
    }

    @Override
    public TaskCategory getTaskCategoryById(Integer id) {
        return taskCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task category with id " + id + " not found"));
    }

    @Override
    public TaskCategory createTaskCategory(TaskCategory taskCategory) {
        taskCategory.setId(null);
        return taskCategoryRepository.save(taskCategory);
    }

    @Override
    public TaskCategory updateTaskCategory(Integer id, TaskCategory taskCategory) {
        if (!taskCategoryRepository.existsById(id))
            throw new EntityNotFoundException("Task category with id " + id + " not found");
        taskCategory.setId(id);
        return taskCategoryRepository.save(taskCategory);
    }

    @Override
    public void deleteTaskCategory(Integer id) {
        if (!taskCategoryRepository.existsById(id))
            throw new EntityNotFoundException("Task category with id " + id + " not found");
        taskCategoryRepository.deleteById(id);
    }
}
