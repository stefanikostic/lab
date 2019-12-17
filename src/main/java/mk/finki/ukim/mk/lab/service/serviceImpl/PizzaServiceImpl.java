package mk.finki.ukim.mk.lab.service.serviceImpl;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidConsultationSlotIdException;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import mk.finki.ukim.mk.lab.service.PizzaService;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(isVeggie(pizzaId) && veggie) {
            this.pizzaRepository.save(pizza);
            return pizza;
        }
        throw new InvalidConsultationSlotIdException();
    }

    @Override
    public List<Ingredient> sameIngredients(int pizzaId1, int pizzaId2) {
        Pizza pizza1 = this.pizzaRepository.findById(pizzaId1).orElseThrow(InvalidConsultationSlotIdException::new);
        Pizza pizza2 = this.pizzaRepository.findById(pizzaId2).orElseThrow(InvalidConsultationSlotIdException::new);
        List<Ingredient> sameIngredients = new ArrayList<>();
        List<Ingredient> ingredientsPizza1 = pizza1.getIngredients();
        List<Ingredient> ingredientsPizza2 = pizza2.getIngredients();
        for(Ingredient i1 : ingredientsPizza1) {
            for(Ingredient i2 : ingredientsPizza2){
                if(i1.getIngredientId().equals(i2.getIngredientId())){
                    sameIngredients.add(i1);
                    break;
                }
            }
        }
        return sameIngredients;

    }

    @Override
    public List<Pizza> lessThen(int totalIngredients) {
        return this.pizzaRepository.getAllPizzas().stream().filter(p -> p.getIngredients().size() < totalIngredients).collect(Collectors.toList());
    }

    @Override
    public boolean isVeggie(int pizzaId) {
        Pizza pizza = this.pizzaRepository.findById(pizzaId).orElseThrow(InvalidConsultationSlotIdException::new);
        List<Ingredient> ingredients = pizza.getIngredients();
        for(Ingredient i : ingredients){
            if(!i.isVeggie()){
                return false;
            }
        }
        return true;
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
