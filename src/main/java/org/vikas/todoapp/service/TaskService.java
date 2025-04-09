package org.vikas.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vikas.todoapp.dto.TaskDTO;
import org.vikas.todoapp.model.Task;
import org.vikas.todoapp.model.User;
import org.vikas.todoapp.repo.TaskRepo;
import org.vikas.todoapp.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public List<TaskDTO> getTasksByUserName(String username) {
        List<Task> tasks=        taskRepo.findTasksByUser_Username(username);
        List<TaskDTO> taskDTOs= new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDueDate(task.getDueDate());
            taskDTO.setPriority(task.getPriority());
            taskDTO.setCompleted(task.isCompleted());
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }


    public String saveTask(TaskDTO taskdto, String username) {
        User user= userRepo.findByUsername(username);

        Task task=new Task();
        task.setTitle(taskdto.getTitle());
        task.setDescription(taskdto.getDescription());
        task.setDueDate(taskdto.getDueDate());
        task.setPriority(taskdto.getPriority());
        task.setCompleted(taskdto.isCompleted());
        task.setUser(user);

        taskRepo.save(task);
         return "success";
    }
}
