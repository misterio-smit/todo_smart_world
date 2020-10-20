package com.todo.smart.worldssp.todo.repo;

import com.todo.smart.worldssp.todo.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepo extends JpaRepository<ListEntityTodo, Long> {

}
