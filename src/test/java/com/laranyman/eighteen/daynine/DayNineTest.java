package com.laranyman.eighteen.daynine;

import com.laranyman.eighteen.DayIfc;
import org.junit.Test;

import static com.laranyman.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayNineTest
{
    @Test
    public void testPartOneExampleZero ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 32, dayNine.partOne ( 9, 25 ) );
    }

    @Test
    public void testPartOneExampleOne ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 8317, dayNine.partOne ( 10, 1618 ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 146373, dayNine.partOne ( 13, 7999 ) );
    }

    @Test
    public void testPartOneExampleThree ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 2764, dayNine.partOne ( 17, 1104 ) );
    }

    @Test
    public void testPartOneExampleFour ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 54718, dayNine.partOne ( 21, 6111 ) );
    }

    @Test
    public void testPartOneExampleFive ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 37305, dayNine.partOne ( 30, 5807 ) );
    }

    @Test
    public void testPartTwoExampleZero ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 32, dayNine.partOne ( 9, 25 ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 8317, dayNine.partOne ( 10, 1618 ) );
    }

    @Test
    public void testPartTwoExampleTwo ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 146373, dayNine.partOne ( 13, 7999 ) );
    }

    @Test
    public void testPartTwoExampleThree ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 2764, dayNine.partOne ( 17, 1104 ) );
    }

    @Test
    public void testPartTwoExampleFour ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 54718, dayNine.partOne ( 21, 6111 ) );
    }

    @Test
    public void testPartTwoExampleFive ( )
    {
        DayNine dayNine = new DayNine ( );
        assertEqual ( 37305, dayNine.partOne ( 30, 5807 ) );
    }
}
