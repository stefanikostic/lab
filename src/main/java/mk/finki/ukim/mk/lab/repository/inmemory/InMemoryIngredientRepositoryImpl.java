package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.DataHolder;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.vm.Page;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("in-memory-repo1")
@Repository
public class InMemoryIngredientRepositoryImpl implements IngredientRepository {
    @Override
    public Optional<Ingredient> findById(String ingredientId) {
        return DataHolder.ingredients.stream().filter(i->i.getIngredientId().equals(ingredientId)).findFirst();
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        this.findById(ingredient.getIngredientId()).ifPresent(DataHolder.ingredients::remove);
        DataHolder.ingredients.add(ingredient);
        return ingredient;
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        return Page.slice(DataHolder.ingredients.stream().sorted().collect(Collectors.toList()), page, size);
    }

    @Override
    public Page<Ingredient> getAllSpicyIngredients(int page, int size) {
        return Page.slice(DataHolder.ingredients.stream().filter(s->s.isSpicy()).collect(Collectors.toList()), page, size);
    }

    @Override
    public List<Ingredient> getSpicyIngredients() {
        return null;
    }

    @Override
    public List<Ingredient> getAll() {
        return null;
    }

    @Override
    public void deleteById(String id) {
        this.findById(id).ifPresent(DataHolder.ingredients::remove);
    }
}
