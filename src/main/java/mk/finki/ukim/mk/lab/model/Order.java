package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order {
    private String pizzaType;
    private String clientName;
    private String clientAddress;
    private Long orderId;
}
