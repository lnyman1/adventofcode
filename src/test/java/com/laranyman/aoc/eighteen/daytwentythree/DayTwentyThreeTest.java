package com.laranyman.aoc.eighteen.daytwentythree;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;

/**
 * @author Lara
 */
public class DayTwentyThreeTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "pos=<0,0,0>, r=4\n" +
                       "pos=<1,0,0>, r=1\n" +
                       "pos=<4,0,0>, r=3\n" +
                       "pos=<0,2,0>, r=1\n" +
                       "pos=<0,5,0>, r=3\n" +
                       "pos=<0,0,3>, r=1\n" +
                       "pos=<1,1,1>, r=1\n" +
                       "pos=<1,1,2>, r=1\n" +
                       "pos=<1,3,1>, r=1";

        DayTwentyThree dayTwentyThree = new DayTwentyThree ( );
        assertEqual ( 7, dayTwentyThree.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "pos=<10,12,12>, r=2\n" +
                       "pos=<12,14,12>, r=2\n" +
                       "pos=<16,12,12>, r=4\n" +
                       "pos=<14,14,14>, r=6\n" +
                       "pos=<50,50,50>, r=200\n" +
                       "pos=<10,10,10>, r=5";
        DayTwentyThree dayTwentyThree = new DayTwentyThree ( );
        assertEqual ( 36, dayTwentyThree.partTwo ( input ) );
    }
}
