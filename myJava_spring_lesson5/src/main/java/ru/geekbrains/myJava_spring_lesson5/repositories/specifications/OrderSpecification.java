package ru.geekbrains.myJava_spring_lesson5.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.myJava_spring_lesson5.entities.Order;


public class OrderSpecification {

    public static Specification<Order> currentPriceGreaterOrEqualsThan(int minPrice) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("currentPrice"), minPrice);  // where o.currentprice >= minPrice
    }

    public static Specification<Order> currentPriceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("currentPrice"), maxPrice); // where o.currentprice <= maxPrice
    }

    public static Specification<Order> codeLike(String codePart) {
        return (Specification<Order>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("code"), String.format("%%%s%%", codePart)); // where o.code like %codePart%
    }


}
