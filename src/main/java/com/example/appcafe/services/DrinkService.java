package com.example.appcafe.services;

import com.example.appcafe.models.Category;
import com.example.appcafe.models.Drink;
import com.example.appcafe.repository.CategoryRepository;
import com.example.appcafe.repository.DrinkRepository;
import com.example.appcafe.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkService {

    private final DrinkRepository drinkRepository;
    private final CategoryRepository categoryRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Optional<Drink> getDrinkById(Long id) {
        return drinkRepository.findById(id);
    }

    public Drink createDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    public Drink updateDrink(Long id, Drink drink) {
        drink.setId(id);
        return drinkRepository.save(drink);
    }

    public void deleteDrink(Long id) {
        Drink drink = drinkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đồ uống"));

        // Kiểm tra nếu đã có trong đơn hàng
        if (orderItemRepository.existsByDrink(drink)) {
            throw new RuntimeException("Không thể xoá: Đồ uống đã được sử dụng trong đơn hàng.");
        }

        drinkRepository.delete(drink);
    }


    public List<Drink> searchDrinksByName(String name) {
        return drinkRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Drink> searchDrinksByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(drinkRepository::findByCategory).orElse(List.of());
    }
}