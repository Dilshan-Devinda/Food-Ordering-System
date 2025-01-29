package com.Order.DineDash.Request;

import com.Order.DineDash.model.Address;

public class OrderRequest {

    private Long restaurantId;
    private Address deliveryAddress;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
