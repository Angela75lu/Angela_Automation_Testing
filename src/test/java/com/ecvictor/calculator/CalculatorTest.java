package com.ecvictor.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void add() throws Exception {
        assertEquals("1+2 should equals to 3", 3, Calculator.add(1, 2));
        assertTrue("1+2 should equals to 3", 3 == Calculator.add(1, 2));
        assertNotEquals("1+2 should not equal to 4", 4, Calculator.add(1, 2));

    }

    @Test
    public void divide() throws Exception {
        Calculator calculator = new Calculator();
        assertTrue("3/2 should be 1", 1 == calculator.divide(3, 2));
    }
    @Test
    public void divideZero() throws Exception {
        Calculator calculator = new Calculator();
        assertTrue("3/0 should be 0", 0 == calculator.divide(3, 0));
    }

    @Test
    public void multiple() throws Exception {
        Calculator calculator=new Calculator();
        calculator.multiple(3,4);
//        assertEquals("3*4 should be 12",12,calculator.multiple(3,4));
    }

}