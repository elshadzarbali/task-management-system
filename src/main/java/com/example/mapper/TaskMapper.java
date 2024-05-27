package com.example.mapper;

import com.example.dto.TaskDTO;
import com.example.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private final UserMapper userMapper;
    private final TaskCategoryMapper taskCategoryMapper;

    @Autowired
    public TaskMapper(UserMapper userMapper, TaskCategoryMapper taskCategoryMapper) {
        this.userMapper = userMapper;
        this.taskCategoryMapper = taskCategoryMapper;
    }

    public TaskDTO toTaskDTO(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .priority(task.getPriority())
                .user(userMapper.toUserDTO(task.getUser()))
                .taskCategory(taskCategoryMapper.toTaskCategoryDTO(task.getTaskCategory()))
                .build();
    }

    public Task toTask(TaskDTO taskDTO) {
        return Task.builder()
                .id(taskDTO.getId())
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .dueDate(taskDTO.getDueDate())
                .status(taskDTO.getStatus())
                .priority(taskDTO.getPriority())
                .user(userMapper.toUser(taskDTO.getUser()))
                .taskCategory(taskCategoryMapper.toTaskCategory(taskDTO.getTaskCategory()))
                .build();
    }
}
