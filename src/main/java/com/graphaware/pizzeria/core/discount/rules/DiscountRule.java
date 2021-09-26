package com.graphaware.pizzeria.core.discount.rules;

import com.graphaware.pizzeria.model.Pizza;

import java.util.List;

public interface DiscountRule {

    /**
     * Apply discount rule
     * @param pizzas
     * @return discount value
     */
    Double apply(List<Pizza> pizzas);

}
