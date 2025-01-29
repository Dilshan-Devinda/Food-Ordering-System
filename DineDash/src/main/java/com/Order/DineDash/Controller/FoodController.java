package com.Order.DineDash.Controller;

import com.Order.DineDash.Request.CreateFoodRequest;
import com.Order.DineDash.Service.FoodService;
import com.Order.DineDash.Service.RestaurantService;
import com.Order.DineDash.Service.UserService;
import com.Order.DineDash.model.Food;
import com.Order.DineDash.model.Restaurant;
import com.Order.DineDash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {


    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;

    @GetMapping ("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods= foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.CREATED);
    }

    @GetMapping ("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonveg,
            @RequestParam(required = false) String food_Category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        List<Food> foods= foodService.getRestaurantFood(restaurantId,vegetarian,seasonal,nonveg,food_Category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
