package com.laranyman.aoc.eighteen.daythree;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayThreeTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "#1 @ 1,3: 4x4\n" +
                       "#2 @ 3,1: 4x4\n" +
                       "#3 @ 5,5: 2x2";

        DayThree dayThree = new DayThree ( );

        assertEqual ( 4, dayThree.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "#1 @ 1,3: 4x4\n" +
                       "#2 @ 3,1: 4x4\n" +
                       "#3 @ 5,5: 2x2";

        DayThree dayThree = new DayThree ( );

        assertEqual ( "3", dayThree.partTwo ( input ) );
    }
}
