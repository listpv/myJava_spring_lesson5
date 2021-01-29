package ru.geekbrains.myJava_spring_lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.myJava_spring_lesson5.entities.Order;
import ru.geekbrains.myJava_spring_lesson5.entities.Product;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("select o from Order o where o.customer.id = ?1")
    List<Order> findAllByCustomerId(Long customerId);
}
