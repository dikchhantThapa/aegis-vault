package com.ecom.service.impl;

import com.ecom.model.Category;
import com.ecom.repository.CategoryRepository;
import com.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean deleteCategory(int id) {
        // if id not found, return null(return false)
        Category category = categoryRepository.findById(id).orElse(null);

        // if that game is in the DB (not null)
        if(!ObjectUtils.isEmpty(category)) {
            categoryRepository.delete(category);
            return true;    // sends true (already deleted) to Admin Controller
        }
        return null;
    }

    @Override
    public Boolean existCategory(String name) {
        return categoryRepository.existsByName(name);
    }
}
