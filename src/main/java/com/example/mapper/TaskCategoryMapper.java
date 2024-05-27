package com.example.mapper;

import com.example.dto.TaskCategoryDTO;
import com.example.entity.TaskCategory;
import org.springframework.stereotype.Component;

@Component
public class TaskCategoryMapper {
    public TaskCategoryDTO toTaskCategoryDTO(TaskCategory taskCategory) {
        if (taskCategory == null) return null;
        return TaskCategoryDTO.builder()
                .id(taskCategory.getId())
                .name(taskCategory.getName())
                .build();
    }

    public TaskCategory toTaskCategory(TaskCategoryDTO taskCategoryDTO) {
        if (taskCategoryDTO == null) return null;
        return TaskCategory.builder()
                .id(taskCategoryDTO.getId())
                .name(taskCategoryDTO.getName())
                .build();
    }
}
