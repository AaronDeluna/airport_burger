package com.javaacademy.burger.dish;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * Тип блюда
 */
@Getter
public enum DishType {
    BURGER(BigDecimal.valueOf(300)), //Бургер
    RIBS(BigDecimal.valueOf(700)), //Ребра
    FRIED_POTATO(BigDecimal.valueOf(200)), //Жареная картошка;
    FUAGRA(BigDecimal.valueOf(0)); //Фуагра (на данный момент ресторан не готовит фуагра)

    private final BigDecimal price; //Цена

    DishType(BigDecimal price) {
        this.price = price;
    }
}
