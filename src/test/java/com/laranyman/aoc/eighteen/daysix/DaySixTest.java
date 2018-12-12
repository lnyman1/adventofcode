package com.laranyman.aoc.eighteen.daysix;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DaySixTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "1, 1\n" +
                       "1, 6\n" +
                       "8, 3\n" +
                       "3, 4\n" +
                       "5, 5\n" +
                       "8, 9";

        DaySix daySix = new DaySix ();

        assertEqual ( 17, daySix.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "1, 1\n" +
                       "1, 6\n" +
                       "8, 3\n" +
                       "3, 4\n" +
                       "5, 5\n" +
                       "8, 9";

        DaySix daySix = new DaySix ();

        assertEqual ( 16, daySix.partTwo ( input, 32 ) );
    }
}
