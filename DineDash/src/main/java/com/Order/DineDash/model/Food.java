package com.Order.DineDash.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private long price;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    @ElementCollection //create separate food-images
    private List<String> images;

    private  boolean available;

    @ManyToOne
    private Restaurant restaurant;


    @Column(name = "isvegetarian", nullable = false)
    private boolean vegetarian;
    private boolean IsSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredients= new ArrayList<>();

    private Date creationDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Category getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(Category foodCategory) {
        this.foodCategory = foodCategory;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isVegetarian() {
        return vegetarian; // Lowercase field name
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }


    public boolean isSeasonal() {
        return IsSeasonal;
    }

    public void setSeasonal(boolean seasonal) {
        IsSeasonal = seasonal;
    }

    public List<IngredientsItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsItem> ingredients) {
        this.ingredients = ingredients;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
