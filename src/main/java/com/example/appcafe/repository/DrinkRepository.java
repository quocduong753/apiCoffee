package com.example.appcafe.repository;

import com.example.appcafe.models.Category;
import com.example.appcafe.models.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByNameContainingIgnoreCase(String name);
    List<Drink> findByCategory(Category category);
    boolean existsByCategory(Category category);

}