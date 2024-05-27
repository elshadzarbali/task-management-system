package com.example.dto;

import com.example.enums.Priority;
import com.example.enums.Status;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private Instant dueDate;
    private Priority priority;
    private Status status;
    private UserDTO user;
    private TaskCategoryDTO taskCategory;
}
