package com.Order.DineDash.Service;

import com.Order.DineDash.Request.OrderRequest;
import com.Order.DineDash.model.Order;
import com.Order.DineDash.model.User;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(long orderId, String orderStatus) throws Exception;

    public void cancelOrder(long orderId) throws Exception;

    public List<Order> getUserOrder(Long userId) throws Exception;

    public List<Order> getRestaurantOrder(Long restaurantId,String orderStatus) throws Exception;

    public Order findOrderById(long orderId) throws Exception;
}
