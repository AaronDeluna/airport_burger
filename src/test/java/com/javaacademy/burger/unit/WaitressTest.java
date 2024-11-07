package com.javaacademy.burger.unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FUAGRA;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WaitressTest {

    private Kitchen kitchen;
    private Waitress waitress;

    @BeforeEach
    void setUp() {
        kitchen = Mockito.mock(Kitchen.class);
        waitress = new Waitress();
    }

    @Test
    @DisplayName("Был запрошен бургер, официант принял заказ")
    void waiterAcceptsBurgerOrder() {
        assertTrue(waitress.giveOrderToKitchen(BURGER, kitchen));
    }

    @Test
    @DisplayName("Была запрошена фуагра, официант не принял заказ")
    void waiterDoesNotAcceptFoieGrasOrder() {
        assertFalse(waitress.giveOrderToKitchen(FUAGRA, kitchen));
    }
}