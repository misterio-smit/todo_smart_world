package com.todo.smart.worldssp.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность дела
 */
@Entity
@Data
@Table(name = "todo_table")

public class EntityTodo {

    /**
     * Пустой конструктор
     */
    public EntityTodo() {
    }

    /**
     * @param listId UUID списка дел к которому привяжется дело
     * @param shortDescription краткое описание дела
     * @param name название дела
     * @param priority срочность
     */
    public EntityTodo(UUID listId, String shortDescription, String name, Short priority) {
        this.listId = listId;
        this.shortDescription = shortDescription;
        this.name = name;
        this.priority = priority;
    }

    @Id
    @Column(name = "_id")
    private UUID id;

    @Column(name = "list_id")
    private UUID listId;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "create_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationDate;

    @Column(name = "mod_data")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    @Column(name = "done")
    private boolean done = false;

    @Column(name = "priority")
    private Short priority;



}
