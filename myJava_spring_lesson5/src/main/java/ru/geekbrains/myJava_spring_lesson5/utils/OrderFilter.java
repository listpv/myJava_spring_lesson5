package ru.geekbrains.myJava_spring_lesson5.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.myJava_spring_lesson5.entities.Order;
import ru.geekbrains.myJava_spring_lesson5.repositories.specifications.OrderSpecification;

import java.util.Map;

public class OrderFilter {

    private Specification<Order> spec;
    private String filterDefinition;

    public OrderFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterCode = params.get("code");

        if (filterCode != null && !filterCode.isEmpty()) {
            spec = spec.and(OrderSpecification.codeLike(filterCode));
            filterDefinitionBuilder.append("&code=").append(filterCode);
        }

        String filterName = params.get("customerName");

        if(filterName != null && !filterName.isEmpty()){
            spec = spec.and(OrderSpecification.nameLike(filterName));
            filterDefinitionBuilder.append("&customerName=").append(filterName);
        }


        if (params.containsKey("min_price") && !params.get("min_price").isEmpty()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(OrderSpecification.currentPriceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isEmpty()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(OrderSpecification.currentPriceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        filterDefinition = filterDefinitionBuilder.toString();

        System.out.println(filterDefinition);
    }

    public Specification<Order> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Order> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}
