package com.yourcompany.cau3.controller;

import com.yourcompany.cau3.model.entity.Task;
import com.yourcompany.cau3.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET /tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.findAllTasks(), HttpStatus.OK);
    }

    // GET /tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Optional<Task> taskOpt = taskService.findTaskById(id);
        if (taskOpt.isPresent()) {
            return new ResponseEntity<>(taskOpt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Không tìm thấy công việc có ID: " + id, HttpStatus.NOT_FOUND);
    }

    // POST /tasks
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task newTask) {
        try {
            Task createdTask = taskService.createTask(newTask);
            return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task updatedData) {
        try {
            Task updatedTask = taskService.updateTask(id, updatedData);
            if (updatedTask != null) {
                return new ResponseEntity<>(updatedTask, HttpStatus.OK);
            }
            return new ResponseEntity<>("Không thể cập nhật. Không tìm thấy công việc có ID: " + id, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskService.deleteTaskById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Xóa công việc thành công!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Không thể xóa. Không tìm thấy công việc có ID: " + id, HttpStatus.NOT_FOUND);
    }
}