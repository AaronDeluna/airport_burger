package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Бургерная
 */
@AllArgsConstructor
@Slf4j
public class Steakhouse {
    private final Waitress waitress;
    private final Kitchen kitchen;
    private final PayTerminal payTerminal;

    /**
     * Прием заказа
     */
    public Paycheck makeOrder(DishType dishType, Currency currency) {
        log.info("Клиент: Здравствуйте, хочу заказать: {}", dishType);
        boolean isOrderTook = waitress.giveOrderToKitchen(dishType, kitchen);
        if (!isOrderTook) {
            log.warn("Администратор: Пожалуйста выберите другое блюдо");
            return null;
        }
        return payTerminal.pay(dishType, currency);
    }

    /**
     * Взятие заказа
     */
    public Dish takeOrder(Paycheck paycheck) {
        log.info("Клиент: хочу забрать свой заказ!");
        return waitress.takeDishFromKitchen(paycheck.getDishType(), kitchen);
    }
}
