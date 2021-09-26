package com.graphaware.pizzeria.core.discount;

import com.graphaware.pizzeria.model.Pizza;

import java.util.List;

public interface DiscountService {
    /**
     * Calculate any available discount for the pizzas
     * @param pizzas
     * @return discount value
     */
    Double determineDiscount(final List<Pizza> pizzas);
}
