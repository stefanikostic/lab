package mk.finki.ukim.mk.lab.service.serviceImpl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidConsultationSlotIdException;
import mk.finki.ukim.mk.lab.repository.IngredientRepository;
import mk.finki.ukim.mk.lab.service.IngredientService;
import mk.finki.ukim.mk.lab.vm.Page;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Page<Ingredient> getAllIngredients(int page, int size) {
        return this.ingredientRepository.getAllIngredients(page, size);
    }

    @Override
    public List<Ingredient> getAll() {
        return this.ingredientRepository.getAll();
    }

    @Override
    public Page<Ingredient> getAllSpicyIngredients(int page, int size) {
        return this.ingredientRepository.getAllSpicyIngredients(page, size);
    }

    @Override
    public List<Ingredient> getSpicyIngredients() {
        return this.ingredientRepository.getSpicyIngredients();
    }

    @Override
    public void deleteIngredient(String id) {
        this.ingredientRepository.deleteById(id);
    }

    @Override
    public Ingredient findById(String id) {
        return this.ingredientRepository.findById(id).orElseThrow(InvalidConsultationSlotIdException::new);
    }

    @Override
    public Ingredient createIngredient(String ingredientId, String name, boolean spicy, float amount, boolean veggie) {
        Ingredient ingredient = new Ingredient(ingredientId, name, spicy, amount, veggie);
        if(ingredientRepository.getSpicyIngredients().toArray().length==3 && spicy){
            throw new InvalidConsultationSlotIdException();
        }
        if(ingredientRepository.getAll().stream().anyMatch(i -> i.getName().equals(name))){
            throw new InvalidConsultationSlotIdException();
        }
        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient updateIngredient(String ingredientId, String name, boolean spicy, float amount, boolean veggie) {
        Ingredient ingredient = this.ingredientRepository.findById(ingredientId).orElseThrow(InvalidConsultationSlotIdException::new);
        ingredient.setName(name);
        ingredient.setSpicy(spicy);
        ingredient.setVeggie(veggie);
        ingredient.setAmount(amount);
        return this.ingredientRepository.save(ingredient);
    }
}
