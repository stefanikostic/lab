package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidConsultationSlotIdException;
import mk.finki.ukim.mk.lab.service.IngredientService;
import mk.finki.ukim.mk.lab.service.PizzaService;
import mk.finki.ukim.mk.lab.vm.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/pizzas", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class PizzaApi {
    private final PizzaService pizzaService;

    public PizzaApi(IngredientService ingredientService, PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.listPizzas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza createPizza(@RequestHeader int pizzaId,
                                       @RequestParam("name") String name,
                                       @RequestParam("description") String description,
                                       @RequestParam("ingredients") List<Ingredient> ingredients,
                                       @RequestParam(defaultValue = "false", name="veggie") boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder){
        Pizza result = pizzaService.createPizza(pizzaId, name, description, ingredients, veggie);
        response.setHeader("Location",builder.path("/api/pizzas/{id}").buildAndExpand(result.getPizzaId()).toUriString());
        return result;
    }

    @GetMapping(params = "totalIngredients")
    public List<Pizza> ingredientsLessThan(@RequestParam int totalIngredients){
        return pizzaService.lessThen(totalIngredients);
    }

    @GetMapping(path = "/compare")
    public List<Ingredient> sameIngredients(@RequestParam int pizzaId1, @RequestParam int pizzaId2){
        return pizzaService.sameIngredients(pizzaId1, pizzaId2);
    }
/*    @GetMapping("{id}/pizzas")
    public List<Pizza> getPizzasWithIn(@PathVariable String pizzaId)
    {
        Pizza pizza = this.pizzaService.findById(Integer.parseInt(pizzaId));
        return this.pizzaService.listPizzas().stream().filter(p->p.getIngredients().contains(pizza)).collect(Collectors.toList());
    }*/

    @GetMapping("/{id}")
    public Pizza getPizza(@PathVariable String pizzaId)
    {
        return this.pizzaService.findById(Integer.parseInt(pizzaId));
    }

    @PutMapping("/{id}")
    public Pizza updatePizza(@RequestHeader int pizzaId,
                                  @RequestHeader("name") String name,
                                  @RequestHeader("description") String description,
                                  @RequestHeader("ingredients") List<Ingredient> ingredients,
                                  @RequestHeader("veggie") boolean veggie) {
        return this.pizzaService.updateIngredient(pizzaId, name, description, ingredients, veggie);
    }

    @DeleteMapping("/{id}")
    public void deleteSlot(@PathVariable String pizzaId) {
        pizzaService.deletePizza(Integer.parseInt(pizzaId));
    }
}
