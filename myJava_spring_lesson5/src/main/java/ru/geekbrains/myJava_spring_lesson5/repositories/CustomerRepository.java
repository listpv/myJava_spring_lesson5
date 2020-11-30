package ru.geekbrains.myJava_spring_lesson5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.myJava_spring_lesson5.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
