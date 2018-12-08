package com.laranyman.eighteen.dayfive;

import org.junit.Test;

import static com.laranyman.eighteen.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayFiveTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "aA";
        DayFive dayFive = new DayFive ( );
        assertEqual ( 0, dayFive.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "abBA";
        DayFive dayFive = new DayFive ( );
        assertEqual ( 0, dayFive.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleThree ( )
    {
        String input = "abAB";
        DayFive dayFive = new DayFive ( );
        assertEqual ( 4, dayFive.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFour ( )
    {
        String input = "aabAAB";
        DayFive dayFive = new DayFive ( );
        assertEqual ( 6, dayFive.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFive ( )
    {
        String input = "dabAcCaCBAcCcaDA";
        DayFive dayFive = new DayFive ( );
        assertEqual ( 10, dayFive.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "dabAcCaCBAcCcaDA";
        DayFive dayFive = new DayFive ( );
        assertEqual ( 4, dayFive.partTwo ( input ) );
    }
}
