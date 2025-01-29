package com.Order.DineDash.Service;

import com.Order.DineDash.Request.CreateFoodRequest;
import com.Order.DineDash.model.Category;
import com.Order.DineDash.model.Food;
import com.Order.DineDash.model.Restaurant;
import lombok.Lombok;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegetarian,
                                        boolean isNOnveg,
                                        boolean isSeasonal,
                                        String foodCategory) throws Exception;

    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailability(Long foodId) throws Exception;
}
