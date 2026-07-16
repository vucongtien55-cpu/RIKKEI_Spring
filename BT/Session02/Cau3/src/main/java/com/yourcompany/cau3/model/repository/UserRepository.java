package com.yourcompany.cau3.model.repository;

import com.yourcompany.cau3.model.entity.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final List<User> userList = new ArrayList<>(List.of(
            new User(1, "Nguyen Van Nhat", "nhat@gmail.com"),
            new User(2, "Vu Cong Tien", "tien@gmail.com"),
            new User(3, "Tran Thi Trang", "trang@gmail.com")
    ));

    public List<User> findAll() {
        return userList;
    }

    public Optional<User> findById(int id) {
        return userList.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public User save(User newUser) {
        int nextId = userList.stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;
        newUser.setId(nextId);
        userList.add(newUser);
        return newUser;
    }

    public void update(User existingUser, User updatedData) {
        existingUser.setName(updatedData.getName());
        existingUser.setEmail(updatedData.getEmail());
    }

    public boolean deleteById(int id) {
        return userList.removeIf(user -> user.getId() == id);
    }
}