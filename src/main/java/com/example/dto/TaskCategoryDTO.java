package com.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TaskCategoryDTO {
    private Integer id;
    private String name;
}
