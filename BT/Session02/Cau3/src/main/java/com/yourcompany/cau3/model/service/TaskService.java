package com.yourcompany.cau3.model.service;

import com.yourcompany.cau3.model.entity.Task;
import com.yourcompany.cau3.model.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> tasks(){
        return taskRepository.findAll();
    }

}
