package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NoReadyDishInKitchen;
import lombok.extern.slf4j.Slf4j;

import static com.javaacademy.burger.dish.DishType.FUAGRA;

/**
 * Официант
 */
@Slf4j
public class Waitress {

    /**
     * Передача заказа на кухню
     */
    public boolean giveOrderToKitchen(DishType dishType, Kitchen kitchen) {
        if (FUAGRA.equals(dishType)) {
            log.warn("Официант: Уважаемый клиент, на данный момент мы не готовим фуагра");
            return false;
        }
        kitchen.cook(dishType);
        return true;
    }

    /**
     * Берет с кухни готовое блюдо
     */
    public Dish takeDishFromKitchen(DishType dishType, Kitchen kitchen) {
        checkHasReadyDishInKitchen(dishType, kitchen);
        Dish dish = kitchen.getCompletedDishes().get(dishType).poll();
        log.info("Официант: клиент возьмите ваше блюдо!");
        return dish;
    }

    /**
     * Проверка, есть ли на кухне готовое блюдо данного типа
     */
    private void checkHasReadyDishInKitchen(DishType dishType, Kitchen kitchen) {
        if (!kitchen.getCompletedDishes().containsKey(dishType)) {
            throw new NoReadyDishInKitchen("На кухне нет готового блюда: " + dishType.name());
        }
    }
}
