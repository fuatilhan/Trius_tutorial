package trius.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import trius.springframework.domain.Order;
import trius.springframework.services.OrderService;
@Controller
public class OrderController {
    private OrderService orderService;
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @RequestMapping({"/order/list", "/order"})
    public String listOrders(Model model){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("orders", orderService.listAll(email));
        return "order/list";
    }

    @RequestMapping("/order/show/{id}")
    public String getOrder(@PathVariable String id, Model model) {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("order", orderService.create(id,email));
        return "order/show";
    }
    @RequestMapping("/order/delete/{id}")
    public String delete(@PathVariable String id){
        orderService.delete(id);
        return "redirect:/order/list";
    }
    @RequestMapping("/product/buy/{id}")
    public String buy(@PathVariable String id) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Order order=orderService.create(id,email);
        if(order==null){
            throw new Exception("Out of Stock");
        }
        return "redirect:/product/list";
    }
}
