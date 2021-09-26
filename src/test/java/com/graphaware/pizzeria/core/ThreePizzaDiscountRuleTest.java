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
   ;

    @BeforeEach
    public void setUp() {
        DEAREST.setPrice(10d);
        MIDDLE.setPrice(8d);
        CHEAPEST.setPrice(2d);
    }

    @Test
    public void cheapestOfThreePizzasShouldBeFree() {
        //Given
        List<Pizza> threePizzas = new ArrayList<Pizza>();
        threePizzas.add(DEAREST);
        threePizzas.add(CHEAPEST);
        threePizzas.add(MIDDLE);

        ThreePizzaDiscountRule threePizzaDiscountRule = new ThreePizzaDiscountRule(threePizzas);

        //When
        Double price = threePizzaDiscountRule.apply();

        //Then
        assertEquals(DEAREST.getPrice()+MIDDLE.getPrice(), price);

    }

}