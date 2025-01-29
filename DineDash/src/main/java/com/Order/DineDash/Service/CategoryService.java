package com.Order.DineDash.Service;

import com.Order.DineDash.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name, Long userId) throws Exception;
    public List<Category> findCategoryByRestaurantId(Long Id) throws Exception;
    public Category findCategoryById(Long Id) throws Exception;
}
