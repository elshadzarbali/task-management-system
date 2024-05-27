package com.example.controller;

import com.example.dto.TaskCategoryDTO;
import com.example.entity.TaskCategory;
import com.example.mapper.TaskCategoryMapper;
import com.example.service.TaskCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task-categories")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TaskCategoryRestController {
    private final TaskCategoryService taskCategoryService;
    private final TaskCategoryMapper taskCategoryMapper;

    @GetMapping
    public ResponseEntity<List<TaskCategoryDTO>> getAllTaskCategories() {
        List<TaskCategory> taskCategories = taskCategoryService.getAllTaskCategories();
        List<TaskCategoryDTO> taskCategoryDTOs = taskCategories.stream()
                .map(taskCategoryMapper::toTaskCategoryDTO).collect(Collectors.toList());
        return ResponseEntity.ok(taskCategoryDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCategoryDTO> getTaskCategoriesByCategoryId(@PathVariable Integer id) {
        try {
            TaskCategory taskCategory = taskCategoryService.getTaskCategoryById(id);
            return ResponseEntity.ok(taskCategoryMapper.toTaskCategoryDTO(taskCategory));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TaskCategoryDTO> createTaskCategory(@RequestBody TaskCategoryDTO taskCategoryDTO) {
        TaskCategory taskCategory = taskCategoryMapper.toTaskCategory(taskCategoryDTO);
        TaskCategory createdTaskCategory = taskCategoryService.createTaskCategory(taskCategory);
        URI location = URI.create("/api/task-categories/" + createdTaskCategory.getId());
        return ResponseEntity.created(location).body(taskCategoryMapper.toTaskCategoryDTO(createdTaskCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCategoryDTO> updateTaskCategory(@PathVariable Integer id, @RequestBody TaskCategoryDTO taskCategoryDTO) {
        TaskCategory taskCategory = taskCategoryMapper.toTaskCategory(taskCategoryDTO);
        try {
            TaskCategory updatedTaskCategory = taskCategoryService.updateTaskCategory(id, taskCategory);
            return ResponseEntity.ok(taskCategoryMapper.toTaskCategoryDTO(updatedTaskCategory));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskCategory(@PathVariable Integer id) {
        try {
            taskCategoryService.deleteTaskCategory(id);
            return ResponseEntity.ok("Task category with id " + id + " was deleted.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
