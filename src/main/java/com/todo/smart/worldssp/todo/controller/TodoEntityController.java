package com.todo.smart.worldssp.todo.controller;

import com.todo.smart.worldssp.todo.domain.EntityTodo;
import com.todo.smart.worldssp.todo.repo.TodoRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO(Шайдуко): Классам, их методам и свойствам нужно писать Java-doc на русском языке
 */
@RestController
@RequestMapping("message") // TODO(Шайдуко): почему message? Вы же вроде опреритуете словом Todo
public class TodoEntityController { // TODO(Шайдуко): как в TodoListController, в TodoEntityController слово Entity можно опустить

    private final TodoRepo todoRepo;
    // TODO(Шайдуко): Действия с репозиторием нужно вынести в отдельный сервис, подцепленный через интерфейс
    //  это нужно для того чтоб мы всегда реализацию работы с дынными могли подменить - сейчас данные лежат в postrgeSQL
    //  а мы захотим чтоб они лежали в памяти

    @Autowired
    public TodoEntityController(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    // TODO(Шайдуко): методы rest-контроллеров не дожны получать/возвращать сущности (а EntityTodo и ListEntityTodo
    //  это как раз сущности), и вообще контроллеры не должны ничего знать о сущностях
    //  для этого есть другие пути - например DTO

    /**
     * TODO(Шайдуко): Классам, их методам и свойствам нужно писать Java-doc на русском языке
     */
    @GetMapping
    public List<EntityTodo> list(){
        return todoRepo.findAll();
        // TODO(Шайдуко): филтрация, сортировка, пагинация
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
