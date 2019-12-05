package mk.finki.ukim.mk.lab.repository;
import mk.finki.ukim.mk.lab.model.Pizza;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public interface PizzaRepository {
    Optional<Pizza> findById(int pizzaId);
    void save(Pizza pizza);
    List<Pizza> getAllPizzas();
    List<Pizza> searchPizzas(String term);
    void deleteById(int id);
}
