package com.graphaware.pizzeria.core.discount;

import com.graphaware.pizzeria.core.discount.rules.DiscountRule;
import com.graphaware.pizzeria.core.discount.rules.ThreePizzaDiscountRule;
import com.graphaware.pizzeria.model.Pizza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountServiceImplTest {

    private DiscountServiceImpl discountService;
    private ThreePizzaDiscountRule threePizzaDiscountRule = Mockito.mock(ThreePizzaDiscountRule.class);
    private static final Double EXPECTED_THREE_PIZZA_DISCOUNT = 2.0D;
    private List<Pizza> pizzas;

    @BeforeEach
    public void setup() {

        pizzas = new ArrayList<>();
        pizzas.add(new Pizza());
        pizzas.add(new Pizza());
        pizzas.add(new Pizza());

        List<DiscountRule> discountRules = new ArrayList<>();
        discountRules.add(threePizzaDiscountRule);
        discountService = new DiscountServiceImpl(discountRules);
    }

    @Test
    public void shouldApplyRule() {
        //Given
        Mockito.when(threePizzaDiscountRule.apply(Mockito.anyList()))
                .thenReturn(EXPECTED_THREE_PIZZA_DISCOUNT);
        ArgumentCaptor<List<Pizza>> captor = ArgumentCaptor.forClass(List.class);
        //When
        Double totalDiscount = discountService.determineDiscount(pizzas);

        //Then
        Mockito.verify(threePizzaDiscountRule, Mockito.times(1)).apply(captor.capture());
        List<Pizza> pizzasPassedToDiscountRule = captor.getValue();
        assertEquals(pizzas, pizzasPassedToDiscountRule);
        assertEquals(EXPECTED_THREE_PIZZA_DISCOUNT, totalDiscount);


    }

}