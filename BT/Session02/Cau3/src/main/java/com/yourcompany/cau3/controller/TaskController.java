package com.yourcompany.cau3.controller;

import com.yourcompany.cau3.model.entity.Task;
import com.yourcompany.cau3.model.repository.TaskRepository;
import com.yourcompany.cau3.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(required = false) String search) {

        List<Task> tasks = taskService.tasks();

        if (search != null && !search.isBlank()) {
            tasks = tasks.stream()
                    .filter(task -> task.getTitle()
                            .toLowerCase()
                            .contains(search.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok(tasks);
    }
}
