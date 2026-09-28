package org.example.bookstore_management.services;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.User;
import org.example.bookstore_management.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //Lấy tất cả user
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //Lấy user theo id
    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy User theo id: " + id));
    }

    //Thêm user
    public User createUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User với email " + user.getEmail() + " đã tồn tại.");
        }
        return userRepository.save(user);
    }

    //Cập nhật user
    public User updateUser(long id, User user){
        User user1 = userRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy user theo id: " + id));

        if(!user1.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email " + user.getEmail() + " đã tồn tại.");
        }

        user1.setAddress(user.getAddress());
        user1.setEmail(user.getEmail());

        return userRepository.save(user1);
    }

    //Xóa User
    public void deleteUser(long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy user có id: " + id));
        userRepository.deleteById(id);
    }
}
