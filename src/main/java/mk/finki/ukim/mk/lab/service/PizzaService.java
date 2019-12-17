package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaService {
    List<Pizza> listPizzas();
    List<Pizza> searchPizzas(String term);
    void deletePizza(int id);
    Pizza findById(int id);

    Pizza createPizza(int pizzaId, String name, String description, List<Ingredient> ingredients, boolean veggie);

    List<Ingredient> sameIngredients(int pizzaId1, int pizzaId2);
    List<Pizza> lessThen(int totalIngredients);
    boolean isVeggie(int pizzaId);
    Pizza updateIngredient(int pizzaId, String name, String description, List<Ingredient> ingredients, boolean veggie);
}
