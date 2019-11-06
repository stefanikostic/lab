package mk.finki.ukim.mk.lab.repository;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class PizzaRepository {
    private List<Pizza> pizzas = Arrays.asList(new Pizza("Margherita", "Margherita (tomato sauce, mozzarella)"),
                new Pizza("Carbonara", "Carbonara (fresh cream, mozzarella, bacon)"),
                new Pizza("Vegetariana", "Vegetariana (tomato sauce, mushrooms)"),
                new Pizza("Calzone", "Calzone (Pizza dough, ricotta, pepperoni, pizza sauce, olive oil)"),
                new Pizza("Cheddar", "Cheddar (cheddar, tomato sauce)"),
                new Pizza("Capricciosa", "Capricciosa (tomato sauce, cheese, ham)"),
                new Pizza("Burger Classic", "Burger Classic (barbecue sauce, beef, mozzarella, onions)"),
                new Pizza("Burger Barbecue", "Burger Barbecue (ham, chicken meat, onions)"),
                new Pizza("Pepperoni", "Pepperoni (tomato sauce, mozzarella, sausage)"),
                new Pizza("Quattro Formaggi", "Quattro Formaggi (Taleggio, Mascarpone, Gorgonzola, Parmesan)"));

    public List<Pizza> getAllPizzas(){
        return pizzas;
    }
}
