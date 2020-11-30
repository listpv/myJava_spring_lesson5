package ru.geekbrains.myJava_spring_lesson5.servicies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.myJava_spring_lesson5.entities.Order;
import ru.geekbrains.myJava_spring_lesson5.entities.Product;
import ru.geekbrains.myJava_spring_lesson5.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    public List<Order> findAll() {
//        return orderRepository.findAll();
//    }

    public Page<Order> findAll(Specification<Order> spec, int page, int size) {
        return orderRepository.findAll(spec,
                PageRequest.of(page, size));
    }
}
