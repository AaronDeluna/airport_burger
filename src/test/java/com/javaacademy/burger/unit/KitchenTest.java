package com.javaacademy.burger.unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FUAGRA;

class KitchenTest {

    private Kitchen kitchen;
    private Dish burger;

    @BeforeEach
    void setUp() {
        kitchen = new Kitchen();
        burger = new Dish(BURGER);
    }

    @Test
    @DisplayName("Успешно блюдо появилось на столе готовой еды")
    void testBurgerSuccessfullyPreparedAndServed() {
        kitchen.cook(BURGER);
        Dish result = kitchen.getCompletedDishes().get(BURGER).stream()
                .findFirst()
                .orElseThrow();

        Assertions.assertEquals(burger, result);
    }

    @Test
    @DisplayName("Успешно выбрасывает исключение, если газ отсутствует")
    void testExceptionThrownOnGasShutdown() {
        kitchen.setHasGas(false);
        Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(BURGER));
    }

    @Test
    @DisplayName("Успешно выбрасывает исключение, если была запрошена фуагра")
    void testExceptionThrownForFoieGrasRequest() {
        Assertions.assertThrows(UnsupportedDishTypeException.class, () -> kitchen.cook(FUAGRA));
    }
}