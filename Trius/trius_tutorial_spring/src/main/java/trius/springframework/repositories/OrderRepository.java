package trius.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import trius.springframework.domain.Order;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,String> {
   List<Order>findByEmail(String email);

}
