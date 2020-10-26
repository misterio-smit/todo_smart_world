package com.todo.smart.worldssp.todo.controller;

import com.todo.smart.worldssp.todo.domain.EntityTodo;
import com.todo.smart.worldssp.todo.repo.TodoRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoRepo todoRepo;

    @Autowired
    public TodoController(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @GetMapping
    public List<EntityTodo> list(){
        return todoRepo.findAll();
    }

    @GetMapping("{id}")
    public EntityTodo getOne(@PathVariable("id") EntityTodo entityTodo) {
        return entityTodo;
    }

    @PostMapping
    public EntityTodo create(@RequestBody EntityTodo entityTodo) {
        entityTodo.setCreationDate(LocalDateTime.now());
        return todoRepo.save(entityTodo);
    }

    @PutMapping("{id}")
    public EntityTodo update (
            @PathVariable("id") EntityTodo entityTodoFromDb,
            @RequestBody EntityTodo entityTodo
    ) {
        BeanUtils.copyProperties(entityTodo, entityTodoFromDb, "id");
        entityTodo.setUpdateDate(LocalDateTime.now());
        return todoRepo.save(entityTodoFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") EntityTodo entityTodo) {
        todoRepo.delete(entityTodo);
    }
}
