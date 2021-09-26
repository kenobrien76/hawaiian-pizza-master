package com.graphaware.pizzeria.core.discount.rules;

import com.graphaware.pizzeria.model.Pizza;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ThreePizzaDiscountRule implements DiscountRule {

    public static final Double ZERO_DISCOUNT = 0.0d;

    @Override
    public Double apply(List<Pizza> pizzas) {
        if (null == pizzas || pizzas.size() != 3) return ZERO_DISCOUNT;

        final List<Pizza> pizzasByPriceAscending =  pizzas.stream()
                .filter(pizza -> null != pizza.getPrice())
                .sorted(Comparator.comparingDouble(Pizza::getPrice))
                .collect(Collectors.toList());

        return (3 == pizzasByPriceAscending.size()) ? pizzasByPriceAscending.get(0).getPrice(): ZERO_DISCOUNT;

    }

}
