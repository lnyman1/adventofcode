package com.laranyman.aoc.eighteen.dayeight;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayEightTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        DayEight dayEight = new DayEight ( );
        assertEqual ( 138, dayEight.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";
        DayEight dayEight = new DayEight ( );
        assertEqual ( 66, dayEight.partTwo ( input ) );
    }
}
