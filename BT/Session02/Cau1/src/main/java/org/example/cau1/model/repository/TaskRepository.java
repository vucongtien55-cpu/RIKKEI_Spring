package org.example.cau1.model.repository;

import org.example.cau1.model.entity.Task;
import org.example.cau1.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {

    private List<Task> taskList = new ArrayList<>();

    public TaskRepository() {

        User user1 = new User(1L, "Vu Cong Tien", "tien@gmail.com", "Admin");
        User user2 = new User(2L, "Nguyễn Thanh Vân", "van@gmail.com", "User");
        User user3 = new User(3L, "Nguyễn Văn Nhật", "nhat@gmail.com", "User");

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
}