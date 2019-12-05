package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity(name = "Ingredients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @Column(name="ingredientId")
    private String ingredientId;
    private String name;
    private boolean spicy;
    private float amount;
    private boolean veggie;
}
