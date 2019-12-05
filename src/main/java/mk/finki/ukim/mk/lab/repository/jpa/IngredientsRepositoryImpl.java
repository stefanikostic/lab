package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.vm.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class IngredientsRepositoryImpl implements IngredientRepository {
    private final JpaIngredientRepository repository;

    public IngredientsRepositoryImpl(JpaIngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Ingredient> findById(String ingredientId) {
        return this.repository.findById(ingredientId);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return this.repository.save(ingredient);
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        org.springframework.data.domain.Page<Ingredient> result = this.repository.findAll(PageRequest.of(page, size));
        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public Page<Ingredient> getAllSpicyIngredients(int page, int size) {
        Page<Ingredient> result = this.getAllSpicyIngredients(page, size);

        return new Page<>(page,
                result.getTotalPages(),
                size,
                result.getContent());
    }

    @Override
    public List<Ingredient> getSpicyIngredients() {
        return this.repository.findAll().stream().filter(i->i.isSpicy()).collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> getAll() {
        return this.repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        this.repository.deleteById(id);
    }
}
