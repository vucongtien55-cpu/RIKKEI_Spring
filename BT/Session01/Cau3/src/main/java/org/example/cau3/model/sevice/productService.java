package org.example.cau3.model.sevice;

import org.example.cau3.model.entity.product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class productService {
    // Bọc List.of hoặc Arrays.asList vào trong new ArrayList<>() để cho phép thêm sửa xóa
    private static final List<product> danhSach = new ArrayList<>(List.of(
            new product(1L, "iphone 14", 1.2E7),
            new product(2L, "iphone 14 pro", 1.3E7),
            new product(3L, "iphone 14 pro max", 1.45E7)
    ));

    public List<product> getAllproduct(){
        return danhSach;
    }

    public product addProduct(product newProduct) {
        // 1. Lấy kích thước của danh sách hiện tại (danhSach) và cộng 1 để tạo ID mới
        Long nextId = (Long) (danhSach.size() + 1L);

        // 2. Gán ID vừa tạo cho đối tượng sản phẩm mới nhận vào
        newProduct.setId(nextId);

        // 3. Thêm sản phẩm mới vào danh sách
        danhSach.add(newProduct);

        // 4. Trả về chính sản phẩm đó
        return newProduct;
    }

    public product updateProduct(Long id, product updateProduct){
        for(product p : danhSach){
            if(p.getId().equals(id)){
                p.setName(updateProduct.getName());
                p.setPrice(updateProduct.getPrice());
                return p;
            }
        }
        return null;
    }

    public boolean deleteProduct(Long id){
        return danhSach.removeIf(p -> p.getId().equals(id));
    }
}
