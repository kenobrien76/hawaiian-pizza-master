package com.graphaware.pizzeria.core;

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


    @BeforeEach
    public void setUp() {
        DEAREST.setPrice(10d);
        MIDDLE.setPrice(8d);
        CHEAPEST.setPrice(2d);
    }

    @Test
    public void cheapestOfThreePizzasShouldBeTheDiscountValue() {
        //Given
        List<Pizza> threePizzas = new ArrayList<Pizza>();
        threePizzas.add(DEAREST);
        threePizzas.add(CHEAPEST);
        threePizzas.add(MIDDLE);

        ThreePizzaDiscountRule threePizzaDiscountRule = new ThreePizzaDiscountRule(threePizzas);

        //When
        Double discount = threePizzaDiscountRule.apply();

        //Then
        assertEquals(CHEAPEST.getPrice(), discount);

    }

    @Test
    public void shouldBeDiscountZeroForTwoPizzas() {
        //Given
        List<Pizza> pizzas = new ArrayList<Pizza>();
        pizzas.add(DEAREST);
        pizzas.add(CHEAPEST);

        ThreePizzaDiscountRule threePizzaDiscountRule = new ThreePizzaDiscountRule(pizzas);

        //When
        Double discount = threePizzaDiscountRule.apply();

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }

    @Test
    public void shouldBeDiscountZeroForEmptyPizzas() {
        //Given
        List<Pizza> pizzas = new ArrayList<Pizza>();

        ThreePizzaDiscountRule threePizzaDiscountRule = new ThreePizzaDiscountRule(pizzas);

        //When
        Double discount = threePizzaDiscountRule.apply();

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }


    @Test
    public void shouldBeDiscountZeroForNullPizzas() {
        //Given
        List<Pizza> pizzas =  null;

        ThreePizzaDiscountRule threePizzaDiscountRule = new ThreePizzaDiscountRule(pizzas);

        //When
        Double discount = threePizzaDiscountRule.apply();

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }

    @Test
    public void shouldBeDiscountZeroForNullPizzasPrice() {
        //Given
        List<Pizza> pizzas =  null;

        ThreePizzaDiscountRule threePizzaDiscountRule = new ThreePizzaDiscountRule(pizzas);

        //When
        Double discount = threePizzaDiscountRule.apply();

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);

    }


    @Test
    public void assumeShouldNotApplyDiscountForGreaterThanThreePizzas() {
        //Given
        List<Pizza> threePizzas = new ArrayList<Pizza>();
        threePizzas.add(DEAREST);
        threePizzas.add(CHEAPEST);
        threePizzas.add(new Pizza());

        ThreePizzaDiscountRule threePizzaDiscountRule = new ThreePizzaDiscountRule(threePizzas);

        //When
        Double discount = threePizzaDiscountRule.apply();

        //Then
        assertEquals(ThreePizzaDiscountRule.ZERO_DISCOUNT, discount);
    }
}