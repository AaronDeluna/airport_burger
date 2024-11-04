package com.javaacademy.burger;

import com.javaacademy.burger.dish.DishType;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Чек об оплате
 */
@Value
public class Paycheck {
    BigDecimal totalAmount; //Итоговая стоимость в валюте
    Currency currency; //Валюта чека
    DishType dishType; //Какое блюдо было оплачено
}
