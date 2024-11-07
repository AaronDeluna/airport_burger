package com.javaacademy.burger.unit;

import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.BURGER;
import static org.junit.jupiter.api.Assertions.*;

class PayTerminalTest {

    private PayTerminal terminal;

    @BeforeEach
    void setUp() {
        terminal = new PayTerminal();
    }

    @Test
    @DisplayName("Успешная оплата бургера в рублях")
    void testSuccessfulBurgerPaymentInRubles() {
        Paycheck expected = new Paycheck(BURGER.getPrice(), RUB, BURGER);
        Paycheck result = terminal.pay(BURGER, RUB);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешно выброс исключения при оплате бургера в мозамбикских долларах")
    void throwsExceptionWhenPayingBurgerWithMozambicanDollars() {
        assertThrows(NotAcceptedCurrencyException.class, () -> terminal.pay(BURGER, MOZAMBICAN_DOLLARS));
    }

}