package ru.geekbrains.myJava_spring_lesson5.controllers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.myJava_spring_lesson5.entities.Order;
import ru.geekbrains.myJava_spring_lesson5.entities.Product;
import ru.geekbrains.myJava_spring_lesson5.servicies.OrderService;
import ru.geekbrains.myJava_spring_lesson5.utils.OrderFilter;
import ru.geekbrains.myJava_spring_lesson5.utils.ProductFilter;

import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "o") Integer page,
                                  @RequestParam Map<String, String> params
    ) {
        if (page < 1) {
            page = 1;
        }
        OrderFilter orderFilter = new OrderFilter(params);
        Page<Order> orders = orderService.findAll(orderFilter.getSpec(), page - 1, 5);
        model.addAttribute("orders", orders);
//        products.get().forEach(System.out::println);
//        System.out.println("products count = " + products.getSize());
        model.addAttribute("filterDefinition", orderFilter.getFilterDefinition());
        return "orders";
    }

//    @GetMapping
//    public String firstRequest(Model model) {
//        model.addAttribute("orders", orderService.findAll());
//        return "orders";
//    }
}
