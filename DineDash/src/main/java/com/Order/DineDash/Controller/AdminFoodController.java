package com.Order.DineDash.Controller;


import com.Order.DineDash.Request.CreateFoodRequest;
import com.Order.DineDash.Response.MessageResponse;
import com.Order.DineDash.Service.FoodService;
import com.Order.DineDash.Service.RestaurantService;
import com.Order.DineDash.Service.UserService;
import com.Order.DineDash.model.Food;
import com.Order.DineDash.model.Restaurant;
import com.Order.DineDash.model.User;
import com.Order.DineDash.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/createFood")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
                                           @RequestHeader ("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId());
        Food food= foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader ("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        foodService.deleteFood(id);
        MessageResponse res=new MessageResponse();
        res.setMessage("food deleted Successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                      @RequestHeader ("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Food food= foodService.updateAvailability(id);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
