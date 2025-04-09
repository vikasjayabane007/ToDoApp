package org.vikas.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vikas.todoapp.dto.TaskDTO;
import org.vikas.todoapp.model.Task;
import org.vikas.todoapp.util.SecurityUtil;
import org.vikas.todoapp.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksForCurrentUser() {

        List<TaskDTO> tasks = taskService.getTasksByUserName(SecurityUtil.getUsername());
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/task")
    public ResponseEntity<String> addTask(@RequestBody TaskDTO taskdto) {

        String status=taskService.saveTask(taskdto,SecurityUtil.getUsername());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
