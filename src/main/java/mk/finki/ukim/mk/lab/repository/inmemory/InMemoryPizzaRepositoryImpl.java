package mk.finki.ukim.mk.lab.repository.inmemory;


import mk.finki.ukim.mk.lab.DataHolder;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("in-memory-repo")
@Repository
public class InMemoryPizzaRepositoryImpl implements PizzaRepository {
    @Override
    public Optional<Pizza> findById(int pizzaId) {
        return DataHolder.pizzas.stream()
                .filter(p->p.getPizzaId()==pizzaId).findFirst();
    }

    @Override
    public void save(Pizza pizza) {
        this.findById(pizza.getPizzaId()).ifPresent(DataHolder.pizzas::remove);
        DataHolder.pizzas.add(pizza);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return new ArrayList<>(DataHolder.pizzas);
    }

    @Override
    public List<Pizza> searchPizzas(String term) {
        return DataHolder.pizzas.stream()
                .filter(r -> r.getName().matches(term))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        this.findById(id).ifPresent(DataHolder.pizzas::remove);
    }
}
