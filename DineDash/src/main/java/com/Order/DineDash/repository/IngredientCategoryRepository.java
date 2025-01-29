package com.Order.DineDash.repository;

import com.Order.DineDash.model.IngredientsCategory;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientsCategory,Long> {

    List<IngredientsCategory> findByRestaurantId(Long id);
}
