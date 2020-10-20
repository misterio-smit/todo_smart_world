package com.todo.smart.worldssp.todo.controller;

import com.todo.smart.worldssp.todo.domain.*;
import com.todo.smart.worldssp.todo.repo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("messages")
public class TodoListController {
    private final TodoListRepo todoListRepo;

    @Autowired
    public TodoListController(TodoListRepo todoListRepo) {
        this.todoListRepo = todoListRepo;
    }

    @GetMapping
    public List<ListEntityTodo> list(){
        return todoListRepo.findAll();
    }

    @GetMapping("{id}")
    public ListEntityTodo getOne(@PathVariable("id") ListEntityTodo listEntityTodo) {
        return listEntityTodo;
    }

    @PostMapping
    public ListEntityTodo create(@RequestBody ListEntityTodo listEntityTodo) {
        listEntityTodo.setListCreationDate(LocalDateTime.now());
        return todoListRepo.save(listEntityTodo);
    }

    @PutMapping("{id}")
    public ListEntityTodo update (
            @PathVariable("id") ListEntityTodo entityTodoFromDb,
            @RequestBody ListEntityTodo listEntityTodo
    ) {
        BeanUtils.copyProperties(listEntityTodo, entityTodoFromDb, "id");
        listEntityTodo.setListUpdateDate(LocalDateTime.now());
         return todoListRepo.save(entityTodoFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") ListEntityTodo listEntityTodo) {
       todoListRepo.delete(listEntityTodo);
    }
}
