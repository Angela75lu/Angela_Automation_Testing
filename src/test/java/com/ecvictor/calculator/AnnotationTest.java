package com.ecvictor.calculator;

import org.junit.*;

import static org.junit.Assert.*;

public class AnnotationTest {
    @BeforeClass
    static public void setUpClass() {
        System.out.println("=======================\n" +
                "Before Class is here.\n");
    }

    @Before
    public void setUp() {
        System.out.println("**************\nBefore test is here.");
    }

    @Test
    public void mainTest() {
        System.out.println("mainTest() test is here.");
    }

    @Test
    public void mainTest2() {
        System.out.println("mainTest2() test is here.");
    }

    @After
    public void tearDown() {
        System.out.println("tearDown test is here.\n" +
                "***************\n");
    }

    @AfterClass
    static public void staticstearDownClass() {
        System.out.println("\n===============\nTear Down Class is here.");
    }
}
