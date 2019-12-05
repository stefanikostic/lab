package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.vm.Page;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Page<Ingredient> getAllIngredients(int page, int size);
    List<Ingredient> getAll();
    Page<Ingredient> getAllSpicyIngredients(int page, int size);
    List<Ingredient> getSpicyIngredients();
    void deleteIngredient(String id);
    Ingredient findById(String id);
    Ingredient createIngredient(String ingredientId, String name, boolean spicy, float amount, boolean veggie);
    Ingredient updateIngredient(String ingredientId, String name, boolean spicy, float amount, boolean veggie);
}
