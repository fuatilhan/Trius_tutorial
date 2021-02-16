package trius.springframework.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import trius.springframework.domain.Order;

import trius.springframework.domain.Product;
import trius.springframework.repositories.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService=productService;
    }


    @Override
    public List<Order> listAll(String email) {
        List<Order> orders = new ArrayList<>();
        orderRepository.findByEmail(email).forEach(orders::add);
        return orders;
    }

    @Override
    public Order create(String id,String email) {
        Product product=productService.getById(id);
        if(product==null){
            return null;
        }
        if(product.getStock().equals(BigDecimal.valueOf(0))){
            return null;
        }
        Order order = new Order();
        order.setProduct(product);
        order.setEmail(email);
        order.setDateCreated(LocalDate.now());
        order.setPrice(product.getPrice());
        orderRepository.save(order);
        product.setStock(product.getStock().subtract(BigDecimal.valueOf(1)));
        productService.saveOrUpdate(product);
        return order;
    }

    @Override
    public void delete(String id) {

        orderRepository.findById(id);
        Optional<Order> order=orderRepository.findById(id);
        if(order.isPresent()){
            Product product=order.get().getProduct();
            product.setStock(product.getStock().add(BigDecimal.valueOf(1)));
            productService.saveOrUpdate(product);
        }
        orderRepository.deleteById(id);

    }


}
