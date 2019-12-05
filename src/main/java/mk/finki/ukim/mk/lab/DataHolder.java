package mk.finki.ukim.mk.lab;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.jpa.JpaIngredientRepository;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {
    public static final List<Ingredient> ingredients = new ArrayList<>();
    public static final List<Pizza> pizzas = new ArrayList<>();
    public static final List<Order> orders = new ArrayList<>();

    public final JpaIngredientRepository ingredientRepository;

    public DataHolder(JpaIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @PostConstruct
    public void init(){
        ingredients.add(new Ingredient("1", "Mozzarela", false, (float) 200.00, true));
        ingredients.add(new Ingredient("1", "Pepperoni", true, (float) 200.00, false));
        ingredients.add(new Ingredient("1", "Ketchup", false, (float) 200.00, true));
        ingredients.add(new Ingredient("1", "Onion", false, (float) 200.00, true));
        ingredients.add(new Ingredient("1", "Garlic", true, (float) 200.00, true));


    this.ingredientRepository.saveAll(ingredients);
    }


}
