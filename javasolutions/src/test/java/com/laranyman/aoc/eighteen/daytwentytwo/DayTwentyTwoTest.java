package com.laranyman.aoc.eighteen.daytwentytwo;

import com.laranyman.aoc.template.DayN;
import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayTwentyTwoTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "depth: 510\n" +
                       "target: 10,10";
        DayTwentyTwo dayTwentyTwo = new DayTwentyTwo ( );
        assertEqual ( 114, dayTwentyTwo.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "depth: 510\n" +
                       "target: 10,10";
        DayTwentyTwo dayTwentyTwo = new DayTwentyTwo ( );
        assertEqual ( 45, dayTwentyTwo.partTwo ( input ) );
    }
}
