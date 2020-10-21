package com.todo.smart.worldssp.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo_list_table")
@Data

public class ListEntityTodo {

    public ListEntityTodo() {
    }

    public ListEntityTodo(Long id, String name, LocalDateTime listCreationDate, LocalDateTime listUpdateDate) {
        this.id = id;
        this.name = name;
        this.listCreationDate = listCreationDate;
        this.listUpdateDate = listUpdateDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    //@ManyToOne
    //private List<EntityTodo> listEntitys;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime listCreationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime listUpdateDate;
}
