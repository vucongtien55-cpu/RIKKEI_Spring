package com.yourcompany.cau3.model.service;

import com.yourcompany.cau3.model.entity.Task;
import com.yourcompany.cau3.model.entity.User;
import com.yourcompany.cau3.model.repository.TaskRepository;
import com.yourcompany.cau3.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task newTask) {

        if (newTask.getUser() == null) {
            throw new IllegalArgumentException("Không thể tạo Task. Thông tin người dùng trống!");
        }

        int userId = newTask.getUser().getId();
        Optional<User> userOpt = userService.findUserById(userId);

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Không thể tạo Task. Người dùng với ID: " + userId + " không tồn tại!");
        }

        newTask.setUser(userOpt.get());
        return taskRepository.save(newTask);
    }

    public Task updateTask(Long id, Task updatedData) {
        Optional<Task> taskOpt = taskRepository.findById(id);
        if (taskOpt.isEmpty()) {
            return null;
        }

        if (updatedData.getUser() == null) {
            throw new IllegalArgumentException("Không thể cập nhật Task. Thông tin người dùng trống!");
        }

        int userId = updatedData.getUser().getId();
        Optional<User> userOpt = userService.findUserById(userId);

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Không thể cập nhật Task. Người dùng với ID: " + userId + " không tồn tại!");
        }

        Task existingTask = taskOpt.get();
        updatedData.setUser(userOpt.get());
        taskRepository.update(existingTask, updatedData);
        return existingTask;
    }

    public boolean deleteTaskById(Long id) {
        return taskRepository.deleteById(id);
    }
}