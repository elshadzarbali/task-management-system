package com.example.entity;

import com.example.enums.Priority;
import com.example.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private Instant dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 45)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 45)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_category_id")
    private TaskCategory taskCategory;

}