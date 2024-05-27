package com.example.controller;

import com.example.dto.TaskDTO;
import com.example.entity.Task;
import com.example.exception.UserNotFoundException;
import com.example.mapper.TaskMapper;
import com.example.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TaskRestController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<Task> tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOs = tasks.stream().map(taskMapper::toTaskDTO).collect(Collectors.toList());
        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable int id) {
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(taskMapper.toTaskDTO(task));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        try {
            Task createdTask = taskService.createTask(taskMapper.toTask(taskDTO));
            URI location = URI.create("api/tasks/" + createdTask.getId());
            return ResponseEntity.created(location).body(taskMapper.toTaskDTO(createdTask));
        } catch (UserNotFoundException | NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable int id) {
        try {
            Task updatedTask = taskService.updateTask(id, taskMapper.toTask(taskDTO));
            return ResponseEntity.ok(taskMapper.toTaskDTO(updatedTask));
        } catch (UserNotFoundException | NullPointerException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok("Task with id " + id + " was deleted");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
