package trius.springframework.services;

import trius.springframework.domain.Order;



import java.util.List;

public interface OrderService {
    List<Order> listAll(String email);
    Order create(String id, String email);
    void delete(String id);

}
