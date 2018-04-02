package com.ccva.utillib;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    public static final String testUrl = "http://192.168.1.121:3000/markets/%s.json?from=app";


    @Test
    public void testString(){

        String s = String.format(testUrl, "etcbth");

        System.out.println(s);

    }


}