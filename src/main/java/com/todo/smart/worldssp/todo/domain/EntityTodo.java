package com.todo.smart.worldssp.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "todo_table")

public class EntityTodo {

    public EntityTodo() {
    }

    public EntityTodo(Long id, String shortDescription, String name, LocalDateTime creationDate, LocalDateTime updateDate, boolean done, Short priority) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.done = done;
        this.priority = priority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shortDescription;

    @Column(nullable = false)
    private String name;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    private boolean done = false;

    private Short priority;



}
