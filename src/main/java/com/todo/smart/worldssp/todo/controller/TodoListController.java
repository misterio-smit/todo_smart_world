package com.todo.smart.worldssp.todo.controller;

import com.todo.smart.worldssp.todo.domain.*;
import com.todo.smart.worldssp.todo.repo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// TODO(Шайдуко): нужен Java-doc на класс и методы
@RestController
@RequestMapping("messages")// TODO(Шайдуко): почему messages? Вы же вроде опреритуете словом TodoList
public class TodoListController {

    private final TodoListRepo todoListRepo;
    // TODO(Шайдуко): Действия с репозиторием нужно вынести в отдельный сервис, подцепленный через интерфейс
    //  это нужно для того чтоб мы всегда реализацию работы с дынными могли подменить - сейчас данные лежат в postrgeSQL
    //  а мы захотим чтоб они лежали в памяти

    @Autowired
    public TodoListController(TodoListRepo todoListRepo) {
        this.todoListRepo = todoListRepo;
    }

    // TODO(Шайдуко): методы rest-контроллеров не дожны получать/возвращать сущности (а EntityTodo и ListEntityTodo
    //  это как раз сущности), и вообще контроллеры не должны ничего знать о сущностях
    //  для этого есть другие пути - например DTO

    @GetMapping
    public List<ListEntityTodo> list(){
        return todoListRepo.findAll();
        // TODO(Шайдуко): филтрация, сортировка, пагинация
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
