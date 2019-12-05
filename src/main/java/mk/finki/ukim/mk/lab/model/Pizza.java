package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
@Entity(name="Pizzas")
public class Pizza {
    @Id
    @Column(name="pizzaId")
    private int pizzaId;
    private String name;
    private String description;
    @ManyToMany(targetEntity = Ingredient.class)
    private List<Ingredient> ingredients;
    private boolean veggie;

}
