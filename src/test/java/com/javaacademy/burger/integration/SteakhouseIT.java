package com.javaacademy.burger.integration;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.Steakhouse;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.Currency.USD;
import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.FRIED_POTATO;
import static com.javaacademy.burger.dish.DishType.RIBS;
import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class SteakhouseTest {

    @Test
    @DisplayName("Успешная покупка бургера за рубли")
    void testSuccessfulTransactionForBurgerInRubles() {
        Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), new PayTerminal());
        Paycheck expectedPaycheck = new Paycheck(BURGER.getPrice(), RUB, BURGER);
        Dish expectedDish = new Dish(BURGER);

        Paycheck resultPaycheck = steakhouse.makeOrder(BURGER, RUB);
        Dish resultDish = steakhouse.takeOrder(resultPaycheck);
        DishType resultDishTypeBurger = resultPaycheck.getDishType();

        assertEquals(expectedPaycheck, resultPaycheck);
        assertEquals(expectedDish, resultDish);
        assertEquals(BURGER, resultDishTypeBurger);
    }

    @Test
    @DisplayName("Успешная покупка ребер за рубли")
    void testSuccessfulRibsPurchaseWithRubles() {
        Paycheck expectedPaycheck = new Paycheck(RIBS.getPrice(), RUB, RIBS);
        Dish expectedDish = new Dish(RIBS);
        PayTerminal payTerminal = Mockito.mock(PayTerminal.class);
        Mockito.when(payTerminal.pay(RIBS, RUB)).thenReturn(expectedPaycheck);

        Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), payTerminal);

        Paycheck resultPaycheck = steakhouse.makeOrder(RIBS, RUB);
        Dish resultDish = steakhouse.takeOrder(resultPaycheck);

        assertEquals(expectedPaycheck, resultPaycheck);
        assertEquals(expectedDish, resultDish);
    }

    @Test
    @DisplayName("Успешная проверка налоговой покупка ребер за рубли")
    void testTaxVerificationForRibsPurchaseInRubles() {
        PayTerminal payTerminal = new PayTerminal();
        Kitchen kitchenMok = Mockito.mock(Kitchen.class);
        Waitress waitressMok = Mockito.mock(Waitress.class);
        Mockito.doReturn(true).when(waitressMok).giveOrderToKitchen(any(), any());
        Steakhouse steakhouse = new Steakhouse(waitressMok, kitchenMok, payTerminal);
        Paycheck expected = new Paycheck(RIBS.getPrice(), RUB, RIBS);
        Paycheck result = steakhouse.makeOrder(RIBS, RUB);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешная проверка налоговой покупка картошки за 1 доллар")
    void testTaxVerificationForPotatoPurchaseInDollars() {
        PayTerminal payTerminal = Mockito.spy(PayTerminal.class);
        Kitchen kitchenMok = Mockito.mock(Kitchen.class);
        Waitress waitressMok = Mockito.mock(Waitress.class);

        Paycheck expected = new Paycheck(valueOf(1), USD, FRIED_POTATO);

        Mockito.doReturn(true).when(waitressMok).giveOrderToKitchen(any(), any());
        Mockito.doReturn(expected).when(payTerminal).pay(FRIED_POTATO, USD);
        Steakhouse steakhouse = new Steakhouse(waitressMok, kitchenMok, payTerminal);

        Paycheck result = steakhouse.makeOrder(FRIED_POTATO, USD);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешная проверка налоговой покупка картошки за 1 мозамбикские доллар")
    void testTaxVerificationForPotatoPurchaseInMozambicanDollar() {
        PayTerminal payTerminalSpy = Mockito.spy(PayTerminal.class);
        Kitchen kitchenMok = Mockito.mock(Kitchen.class);
        Waitress waitressMok = Mockito.mock(Waitress.class);

        Paycheck expected = new Paycheck(valueOf(1), MOZAMBICAN_DOLLARS, BURGER);

        Mockito.doReturn(true).when(waitressMok).giveOrderToKitchen(any(), any());
        Mockito.doReturn(expected).when(payTerminalSpy).pay(BURGER, MOZAMBICAN_DOLLARS);
        Steakhouse steakhouse = new Steakhouse(waitressMok, kitchenMok, payTerminalSpy);

        Paycheck result = steakhouse.makeOrder(BURGER, MOZAMBICAN_DOLLARS);

        assertEquals(expected, result);
    }
}