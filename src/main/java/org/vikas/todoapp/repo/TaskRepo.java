package org.vikas.todoapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vikas.todoapp.model.Task;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task,Integer> {
    List<Task> findTasksByUser_Username(String userUsername);
}
