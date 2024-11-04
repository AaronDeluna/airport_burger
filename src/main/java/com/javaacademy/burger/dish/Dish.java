package com.javaacademy.burger.dish;

import lombok.Data;
import lombok.NonNull;

/**
 * Блюдо
 */
@Data
public class Dish {
    @NonNull
    private final DishType dishType; //Тип блюда
}
