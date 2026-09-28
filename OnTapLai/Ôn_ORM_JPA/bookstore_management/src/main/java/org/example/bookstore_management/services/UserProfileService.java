package org.example.bookstore_management.services;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.UserProfile;
import org.example.bookstore_management.repositories.UserProfileRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    public final UserProfileRepository userProfileRepository;

    //Lấy tất cả
    public List<UserProfile> getAll(){
        return userProfileRepository.findAll();
    }

    //Lấy theo id
    public UserProfile getById(long id){
        return userProfileRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Không tồn tại id: "+ id));
    }

    //Thêm
    public UserProfile crateUserProfile(UserProfile userProfile){
        if(userProfileRepository.existsById(userProfile.getId())){
            throw new RuntimeException("id " + userProfile.getId()+ " đã tồn tại.");
        }
        return userProfileRepository.save(userProfile);
    }
    //Cập nhật
    public UserProfile updateUserProfile(Long id, UserProfile userProfile){
        UserProfile userProfile1 = userProfileRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy id: " + id));
        userProfile1.setId(userProfile.getId());
        userProfile1.setAddress(userProfile.getAddress());
        userProfile1.setPhoneNumber(userProfile.getPhoneNumber());
        return userProfileRepository.save(userProfile);
    }

    //xóa
    public void deleteUserProfile(long id){
        UserProfile userProfile = userProfileRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy id: " + id));
        userProfileRepository.delete(userProfile);
    }
}
