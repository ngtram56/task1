package com.example.task.service;

import com.example.task.model.Brand;
import com.example.task.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

}
