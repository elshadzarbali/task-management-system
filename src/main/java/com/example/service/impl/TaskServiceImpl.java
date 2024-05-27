package com.example.service.impl;

import com.example.entity.Task;
import com.example.exception.UserNotFoundException;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
    }

    @Override
    public Task createTask(Task task) {
        task.setId(null);
        checkTask(task);
        Task savedTask = taskRepository.save(task);
        userRepository.findById(savedTask.getUser().getId()).ifPresent(savedTask::setUser);
        return savedTask;
    }

    @Override
    public Task updateTask(Integer id, Task task) {
        Task foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
        task.setId(foundTask.getId());
        checkTask(task);
        return taskRepository.save(task);
    }

    private void checkTask(Task task) {
        if (task.getUser() == null) {
            throw new NullPointerException("User cannot be null");
        } else if (task.getUser().getId() == null) {
            throw new NullPointerException("User ID cannot be null");
        } else if (!userRepository.existsById(task.getUser().getId())) {
            throw new UserNotFoundException("User with id " + task.getUser().getId() + " does not exist");
        }
    }

    @Override
    public void deleteTask(int id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);
    }
}
