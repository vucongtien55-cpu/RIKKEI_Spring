package com.yourcompany.cau3.model.repository;

import com.yourcompany.cau3.model.entity.Task;
import com.yourcompany.cau3.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    private List<Task> taskList = new ArrayList<>();

    public TaskRepository() {
        User user1 = new User(1, "Nguyen Van Nhat", "nhat@gmail.com");
        User user2 = new User(2, "Vu Cong Tien", "tien@gmail.com");
        User user3 = new User(3, "Tran Thi Trang", "trang@gmail.com");

        taskList.add(new Task(1L, "Làm bài Java", "Collection", "HIGH", user1));
        taskList.add(new Task(2L, "Làm bài Spring", "Controller", "HIGH", user2));
        taskList.add(new Task(3L, "Đọc tài liệu", "REST API", "MEDIUM", user3));
        taskList.add(new Task(4L, "Fix bug", "Login", "HIGH", user1));
        taskList.add(new Task(5L, "Review code", "Project", "LOW", user2));
        taskList.add(new Task(6L, "Viết API", "Task API", "HIGH", user3));
        taskList.add(new Task(7L, "Học SQL", "JOIN", "MEDIUM", user1));
        taskList.add(new Task(8L, "Deploy", "Server", "LOW", user2));
        taskList.add(new Task(9L, "Viết test", "JUnit", "HIGH", user3));
        taskList.add(new Task(10L, "Viết tài liệu", "Markdown", "MEDIUM", user1));
    }

    public List<Task> findAll() {
        return taskList;
    }

    // Tìm kiếm Task theo ID
    public Optional<Task> findById(Long id) {
        return taskList.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    // Thêm mới Task và tự sinh ID
    public Task save(Task newTask) {
        long nextId = taskList.stream()
                .mapToLong(Task::getId)
                .max()
                .orElse(0L) + 1L;
        newTask.setId(nextId);
        taskList.add(newTask);
        return newTask;
    }

    // Cập nhật thông tin Task
    public void update(Task existingTask, Task updatedData) {
        existingTask.setTitle(updatedData.getTitle());
        existingTask.setDescription(updatedData.getDescription());
        existingTask.setPriority(updatedData.getPriority());
        existingTask.setUser(updatedData.getUser());
    }

    // Xóa Task theo ID kiểu Long
    public boolean deleteById(Long id) {
        return taskList.removeIf(task -> task.getId().equals(id));
    }
}