package com.laranyman.aoc.eighteen.daytwentyfive;

import com.laranyman.aoc.template.DayN;
import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayTwentyFiveTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "0,0,0,0\n" +
                       "3,0,0,0\n" +
                       "0,3,0,0\n" +
                       "0,0,3,0\n" +
                       "0,0,0,3\n" +
                       "0,0,0,6\n" +
                       "9,0,0,0\n" +
                       "12,0,0,0";
        DayTwentyFive dayTwentyFive = new DayTwentyFive ( );
        assertEqual ( 2, dayTwentyFive.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "-1,2,2,0\n" +
                       "0,0,2,-2\n" +
                       "0,0,0,-2\n" +
                       "-1,2,0,0\n" +
                       "-2,-2,-2,2\n" +
                       "3,0,2,-1\n" +
                       "-1,3,2,2\n" +
                       "-1,0,-1,0\n" +
                       "0,2,1,-2\n" +
                       "3,0,0,0";
        DayTwentyFive dayTwentyFive = new DayTwentyFive ( );
        assertEqual ( 4, dayTwentyFive.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleThree ( )
    {
        String input = "1,-1,0,1\n" +
                       "2,0,-1,0\n" +
                       "3,2,-1,0\n" +
                       "0,0,3,1\n" +
                       "0,0,-1,-1\n" +
                       "2,3,-2,0\n" +
                       "-2,2,0,0\n" +
                       "2,-2,0,-1\n" +
                       "1,-1,0,-1\n" +
                       "3,2,0,2";
        DayTwentyFive dayTwentyFive = new DayTwentyFive ( );
        assertEqual ( 3, dayTwentyFive.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFour ( )
    {
        String input = "1,-1,-1,-2\n" +
                       "-2,-2,0,1\n" +
                       "0,2,1,3\n" +
                       "-2,3,-2,1\n" +
                       "0,2,3,-2\n" +
                       "-1,-1,1,-2\n" +
                       "0,-2,-1,0\n" +
                       "-2,2,3,-1\n" +
                       "1,2,2,0\n" +
                       "-1,-2,0,-2";
        DayTwentyFive dayTwentyFive = new DayTwentyFive ( );
        assertEqual ( 8, dayTwentyFive.partOne ( input ) );
    }
}
