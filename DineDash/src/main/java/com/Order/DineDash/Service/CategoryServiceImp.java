package com.Order.DineDash.Service;

import com.Order.DineDash.model.Category;
import com.Order.DineDash.model.Restaurant;
import com.Order.DineDash.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {


    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant= restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return categoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long Id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(Id);
        if (optionalCategory.isEmpty()){

            throw new Exception("Category is not found");
        }
        return optionalCategory.get();
    }
}
