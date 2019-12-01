package com.laranyman.aoc.eighteen.dayone;

import com.laranyman.aoc.DayIfc;
import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayOneTest
{

    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "+1\n-2\n+3\n+1";
        DayIfc day = new DayOne ( );
        assertEqual ( 3, day.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "+1\n+1\n+1";
        DayIfc day = new DayOne ( );
        assertEqual ( 3, day.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleThree ( )
    {
        String input = "+1\n+1\n-2";
        DayIfc day = new DayOne ( );
        assertEqual ( 0, day.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFour ( )
    {
        String input = "-1\n-2\n-3";
        DayIfc day = new DayOne ( );
        assertEqual ( -6, day.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "+1\n-2\n+3\n+1";
        DayIfc day = new DayOne ( );
        assertEqual ( 2, day.partTwo ( input ) );
    }

    @Test
    public void testPartTwoExampleTwo ( )
    {
        String input = "+1\n-1";
        DayIfc day = new DayOne ( );
        assertEqual ( 0, day.partTwo ( input ) );
    }

    @Test
    public void testPartTwoExampleThree ( )
    {
        String input = "+3\n+3\n+4\n-2\n-4";
        DayIfc day = new DayOne ( );
        assertEqual ( 10, day.partTwo ( input ) );
    }

    @Test
    public void testPartTwoExampleFour ( )
    {
        String input = "-6\n+3\n+8\n+5\n-6";
        DayIfc day = new DayOne ( );
        assertEqual ( 5, day.partTwo ( input ) );
    }

    @Test
    public void testPartTwoExampleFive ( )
    {
        String input = "+7\n+7\n-2\n-7\n-4";
        DayIfc day = new DayOne ( );
        assertEqual ( 14, day.partTwo ( input ) );
    }
}
