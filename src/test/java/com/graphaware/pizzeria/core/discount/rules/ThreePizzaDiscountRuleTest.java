package com.graphaware.pizzeria.core.discount.rules;

import com.graphaware.pizzeria.core.discount.rules.ThreePizzaDiscountRule;
import com.graphaware.pizzeria.model.Pizza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreePizzaDiscountRuleTest {

    private final Pizza DEAREST = new Pizza();
    private final Pizza MIDDLE = new Pizza();
    private final Pizza CHEAPEST = new Pizza();
    private ThreePizzaDiscountRule rule;


    @BeforeEach
    public void setUp() {
        DEAREST.setPrice(10d);
        MIDDLE.setPrice(8d);
        CHEAPEST.setPrice(2d);
        rule = new ThreePizzaDiscountRule();
    }

    @Test
    public void cheapestOfThreePizzasShouldBeTheDiscountValue() {
        //Given
        List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(DEAREST);
        pizzas.add(CHEAPEST);
        pizzas.add(MIDDLE);

        //When
        Double discount = rule.apply(pizzas);

        //Then
        assertEquals(CHEAPEST.getPrice(), discount);

    }

    @Test
    public void shouldBeDiscountZeroForTwoPizzas() {
        //Given
        List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(DEAREST);
        pizzas.add(CHEAPEST);

        //When
        Double discount = rule.apply(pizzas);

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }

    @Test
    public void shouldBeDiscountZeroForEmptyPizzas() {
        //Given
        List<Pizza> pizzas = new ArrayList<Pizza>();

        //When
        Double discount = rule.apply(pizzas);

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }


    @Test
    public void shouldBeDiscountZeroForNullPizzas() {
        //Given
        List<Pizza> pizzas =  null;

        //When
        Double discount = rule.apply(pizzas);

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }

    @Test
    public void shouldBeDiscountZeroForNullPizzasPrice() {
        //Given
        List<Pizza> pizzas =  null;

        //When
        Double discount = rule.apply(pizzas);

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }


    @Test
    public void assumeShouldNotApplyDiscountForGreaterThanThreePizzas() {
        //Given
        List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(DEAREST);
        pizzas.add(CHEAPEST);
        pizzas.add(new Pizza());

        //When
        Double discount = rule.apply(pizzas);

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);
    }
}