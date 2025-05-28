package com.example.demobuoi1.Buoi1.service;

import com.example.demobuoi1.Buoi1.entity.Category;
import com.example.demobuoi1.Buoi1.repository.CategoryRepo;

import java.util.List;

public class CategoryService {
    private CategoryRepo categoryRepo = new CategoryRepo();

    public List<Category> getAll() {
        return categoryRepo.getAll();
    }

    public Category getOne(Long id) {
        return categoryRepo.getOne(id);
    }

    public void add(Category category) {
        categoryRepo.add(category);
    }

    public void update(Category category) {
        categoryRepo.update(category);
    }

    public void delete(Category category) {
        categoryRepo.delete(category);
    }

    public List<Category> searchName(String name) {
        return categoryRepo.searchName(name);
    }

}
