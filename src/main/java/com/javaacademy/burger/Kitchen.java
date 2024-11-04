package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Кухня
 */
@Setter
@Slf4j
public class Kitchen {
    private boolean hasGas = true; //Есть ли газ на кухне
    @Getter
    private final Map<DishType, Queue<Dish>> completedDishes = new HashMap<>(); //стол готовой еды

    /**
     * Приготовить еду и положить на стол готовой еды
     */
    public void cook(DishType dishType) {
        if (DishType.FUAGRA.equals(dishType)) {
            throw new UnsupportedDishTypeException("Повар не умеет готовить фуагра");
        }
        checkKitchenHasGas();
        Queue<Dish> dishesByType = completedDishes.getOrDefault(dishType, new LinkedList<>());
        Dish dish = new Dish(dishType);
        dishesByType.add(dish);
        completedDishes.put(dishType, dishesByType);
        log.info("Заказ готов: {}", dish);
    }

    /**
     * Есть ли газ на кухне. Если газа нет, готовить не можем.
     */
    private void checkKitchenHasGas() {
        if (!hasGas) {
            throw new KitchenHasNoGasException("На кухне нет газа");
        }
    }
}
