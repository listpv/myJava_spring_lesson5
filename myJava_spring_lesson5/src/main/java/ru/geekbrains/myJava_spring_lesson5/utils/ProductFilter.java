package ru.geekbrains.myJava_spring_lesson5.utils;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.myJava_spring_lesson5.entities.Product;
import ru.geekbrains.myJava_spring_lesson5.repositories.specifications.ProductSpecifications;

import java.util.Map;

public class ProductFilter {

    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        String filterTitle = params.get("title");

        if (filterTitle != null && !filterTitle.isEmpty()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }

        if (params.containsKey("min_price") && !params.get("min_price").isEmpty()) {
            Integer minPrice = Integer.parseInt(params.get("min_price"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
            filterDefinitionBuilder.append("&min_price=").append(minPrice);
        }

        if (params.containsKey("max_price") && !params.get("max_price").isEmpty()) {
            Integer maxPrice = Integer.parseInt(params.get("max_price"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max_price=").append(maxPrice);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Product> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Product> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}
