package com.graphaware.pizzeria.core.discount;

import com.graphaware.pizzeria.core.discount.rules.DiscountRule;
import com.graphaware.pizzeria.model.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final List<DiscountRule> discountRules;

    public DiscountServiceImpl(List<DiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    @Override
    public Double determineDiscount(List<Pizza> pizzas) {
        return discountRules.stream()
                .map(discountRule -> discountRule.apply(pizzas))
                .collect(Collectors.toList())
                .stream().reduce(0.0d, Double::sum);
     }
}
