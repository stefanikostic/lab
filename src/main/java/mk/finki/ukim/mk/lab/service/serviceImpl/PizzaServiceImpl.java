package mk.finki.ukim.mk.lab.service.serviceImpl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidConsultationSlotIdException;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<Pizza> listPizzas() {
        return this.pizzaRepository.getAllPizzas();
    }

    @Override
    public List<Pizza> searchPizzas(String term) {
        return this.pizzaRepository.searchPizzas(term);
    }

    @Override
    public void deletePizza(int id) {
        this.pizzaRepository.deleteById(id);
    }

    @Override
    public Pizza findById(int id) {
        return this.pizzaRepository.findById(id).orElseThrow(InvalidConsultationSlotIdException::new);
    }

    @Override
    public Pizza createPizza(int pizzaId, String name, String description, List<Ingredient> ingredients, boolean veggie) {
        Pizza pizza = new Pizza(pizzaId, name, description, ingredients, veggie);
        this.pizzaRepository.save(pizza);
        return pizza;
    }

    @Override
    public Pizza updateIngredient(int pizzaId, String name, String description, List<Ingredient> ingredients, boolean veggie) {
        Pizza pizza = this.findById(pizzaId);
        pizza.setName(name);
        pizza.setVeggie(veggie);
        pizza.setDescription(description);
        pizza.setIngredients(ingredients);
        this.pizzaRepository.save(pizza);
        return pizza;
    }


}
