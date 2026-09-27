package org.example.bookstore_management.services;

import lombok.RequiredArgsConstructor;
import org.example.bookstore_management.entities.Category;
import org.example.bookstore_management.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //Lấy tất cả cac danh mục
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    //laays theo id
    public Category getById(long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy danh mục có id là:" + id));
    }

    //Thêm danh mục
    public Category createCategory(Category category){
        if(categoryRepository.existsByName(category.getName())){
            throw new RuntimeException("Danh mục" + category.getName() + "đã tồn tại.");
        }
        return categoryRepository.save(category);
    }

    //Cập nhật danh mục
    public Category updateCategory(long id, Category category){
        Category category1 = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Không tìm thấy danh mục theo id: "+ id));

        if(!category1.getName().equals(category.getName()) && categoryRepository.existsByName(category.getName())){
            throw new RuntimeException("Tên danh mục" + category.getName() + " đã tồn tại");
        }

        category1.setName(category.getName());
        //Gán mô tả mới (lấy từ dữ liệu client gửi lên) vào category đang lưu trong database
        category1.setDescription(category.getDescription());

        return categoryRepository.save(category);
    }

    //Xóa danh mục
    public void deleteCategory(Long id){
        Category category = categoryRepository
                .findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy danh mục có id: "+ id));
        categoryRepository.deleteById(id);
    }

}
