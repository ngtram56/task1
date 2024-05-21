package com.example.task.controller;

import com.example.task.model.Brand;
import com.example.task.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping() //don't use verb in path
    public List<Brand> getBrands(){
        return brandService.getAll();
    }

    @PostMapping() //don't use verb in path
    public Brand addBrand (@RequestBody Brand brand){ //RequestBody required using DTO input
        String type = brand.getType().toLowerCase();
		//using enum to handle type
        if (type.equals("car") || type.equals("truck") || type.equals("bus")) {
            brand.setType(type);
            if (brand.getBrandName() == null || brand.getBrandName().isEmpty()) {
                throw new IllegalArgumentException("Tên hãng xe không được bỏ trống");
            }
            return brandService.addBrand(brand);
        } else {
            throw new IllegalArgumentException("Lọại xe không hợp lệ");
        }
    }


}
