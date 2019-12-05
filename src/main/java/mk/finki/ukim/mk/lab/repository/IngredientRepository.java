package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.vm.Page;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    Optional<Ingredient> findById(String ingredientId);
    Ingredient save(Ingredient ingredient);
    Page<Ingredient> getAllIngredients(int page, int size);
   /* Page<Ingredient> getAllSpicyIngredients(int page, int size);
    List<Ingredient> getSpicyIngredients();*/
    List<Ingredient> getAll();
    void deleteById(String id);
}
