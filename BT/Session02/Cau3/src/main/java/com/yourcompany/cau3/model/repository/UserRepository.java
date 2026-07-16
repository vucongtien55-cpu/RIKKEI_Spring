package com.yourcompany.cau3.model.repository;

import com.yourcompany.cau3.model.entity.Task;
import com.yourcompany.cau3.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<User>(List.of(
            new User(1L, "Vu Cong Tien", "tien@gmail.com", "Admin"),
            new User(2L , "Nguyễn Thanh Vân", "van@gmail.com", "User"),
            new User(3L, "Nguyễn Văn Nhật", "nhat@gmail.com", "User")
    ));

    public List<User> getUsers() {
        return users;
    }
}
