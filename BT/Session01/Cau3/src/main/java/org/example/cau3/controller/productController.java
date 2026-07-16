package org.example.cau3.controller;

import org.example.cau3.model.entity.product;
import org.example.cau3.model.sevice.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {

    @Autowired
    private productService productService; // Sửa tên biến thành productService (viết thường chữ p)

    @GetMapping
    public List<product> getProduct() {
        // Gọi qua biến đối tượng productService đã được @Autowired
        return productService.getAllproduct();
    }

    @PostMapping
    public product addProduct(@RequestBody product newproduct) {
        // 1. Gọi qua biến productService (viết thường chữ p)
        // 2. Truyền đối tượng "newproduct" nhận từ client vào, chứ KHÔNG dùng "new product()" rỗng
        return productService.addProduct(newproduct);
    }

    @PutMapping("/{id}")
    public product updateProduct(@PathVariable Long id, @RequestBody product updateProduct){
        return productService.updateProduct(id, updateProduct);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        boolean isDelete = productService.deleteProduct(id);
        if(isDelete){
            return "Xóa thành công";
        } else {
            return "Không tìm thấy sản phẩm có id: " + id;
        }
    }
}
