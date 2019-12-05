package mk.finki.ukim.mk.lab.service;
import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(String pizzaType, String clientName, String address);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
}
