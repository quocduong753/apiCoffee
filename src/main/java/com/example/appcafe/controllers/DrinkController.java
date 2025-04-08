package com.example.appcafe.controllers;

import com.example.appcafe.models.Drink;
import com.example.appcafe.services.DrinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
@RequiredArgsConstructor
public class DrinkController {

    private final DrinkService drinkService;

    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.getAllDrinks();
    }

    @GetMapping("/{id}")
    public Drink getDrink(@PathVariable Long id) {
        return drinkService.getDrinkById(id).orElseThrow();
    }

    @PostMapping
    public Drink createDrink(@RequestBody Drink drink) {
        return drinkService.createDrink(drink);
    }

    @PutMapping("/{id}")
    public Drink updateDrink(@PathVariable Long id, @RequestBody Drink drink) {
        return drinkService.updateDrink(id, drink);
    }

    @DeleteMapping("/{id}")
    public void deleteDrink(@PathVariable Long id) {
        drinkService.deleteDrink(id);
    }

    @GetMapping("/search/name")
    public List<Drink> searchByName(@RequestParam String name) {
        return drinkService.searchDrinksByName(name);
    }

    @GetMapping("/search/category")
    public List<Drink> searchByCategory(@RequestParam Long categoryId) {
        return drinkService.searchDrinksByCategory(categoryId);
    }
}