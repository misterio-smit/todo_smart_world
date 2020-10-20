package com.todo.smart.worldssp.todo.repo;

import com.todo.smart.worldssp.todo.domain.EntityTodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<EntityTodo, Long> {

}
