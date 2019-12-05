package mk.finki.ukim.mk.lab.web.rest;

import mk.finki.ukim.mk.lab.model.Ingredient;
import mk.finki.ukim.mk.lab.model.Pizza;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidConsultationSlotIdException;
import mk.finki.ukim.mk.lab.service.IngredientService;

import mk.finki.ukim.mk.lab.service.PizzaService;
import mk.finki.ukim.mk.lab.vm.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/ingredients", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class IngredientApi {
    private final IngredientService ingredientService;
    private final PizzaService pizzaService;


    public IngredientApi(IngredientService ingredientService, PizzaService pizzaService) {
        this.ingredientService = ingredientService;
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public Page<Ingredient> getAllIngredients(@RequestHeader(name = "page", defaultValue = "0", required = false) int page,
                                              @RequestHeader(name = "page-size", defaultValue = "10", required = false) int size) {
        return ingredientService.getAllIngredients(page, size);
    }

    @GetMapping(params = "spicy")
    public List<Ingredient> getSpicy(@RequestParam boolean spicy){
        return this.ingredientService.getAllSpicyIngredients(spicy);

    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient createIngredient(@RequestHeader String ingredientId,
                                       @RequestHeader("name") String name,
                                       @RequestHeader("spicy") boolean spicy,
                                       @RequestHeader("amount") float amount,
                                       @RequestHeader("veggie") boolean veggie,
                                       HttpServletResponse response,
                                       UriComponentsBuilder builder){
        Ingredient result = ingredientService.createIngredient(ingredientId, name, spicy, amount, veggie);
        response.setHeader("Location",builder.path("/api/ingredients/{id}").buildAndExpand(result.getIngredientId()).toUriString());
        return result;
    }

    @GetMapping("{id}/pizzas")
    public List<Pizza> getPizzasWithIn(@PathVariable String ingredientId)
    {
        Ingredient ingredient = this.ingredientService.findById(ingredientId);
        return this.pizzaService.listPizzas().stream().filter(p->p.getIngredients().contains(ingredient)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable String ingredientId)
    {
        return this.ingredientService.findById(ingredientId);
    }

    @PatchMapping("/{id}")
    public Ingredient updateIngredient(@RequestHeader String ingredientId,
                                       @RequestHeader("name") String name,
                                       @RequestHeader("spicy") boolean spicy,
                                       @RequestHeader("amount") float amount,
                                       @RequestHeader("veggie") boolean veggie) {
        return this.ingredientService.updateIngredient(ingredientId, name, spicy, amount, veggie);
    }

    @DeleteMapping("/{id}")
    public void deleteSlot(@PathVariable String ingredientIdId) {
        ingredientService.deleteIngredient(ingredientIdId);
    }
}
