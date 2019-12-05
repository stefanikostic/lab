package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.repository.PizzaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {
    private final JpaPizzaRepository repository;

    public PizzaRepositoryImpl(JpaPizzaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Pizza> findById(int pizzaId) {
        return this.repository.findById(String.valueOf(pizzaId));
    }

    @Override
    public void save(Pizza pizza) {
        this.repository.save(pizza);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return this.repository.findAll();
    }

    @Override
    public List<Pizza> searchPizzas(String term) {
        return this.repository.findAll().stream().filter(p->p.getName().equals(term)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(int id) {
        this.repository.deleteById(String.valueOf(id));
    }
}
