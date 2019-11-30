package com.laranyman.aoc.eighteen.daytwo;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayTwoTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "abcdef\n" +
                       "bababc\n" +
                       "abbcde\n" +
                       "abcccd\n" +
                       "aabcdd\n" +
                       "abcdee\n" +
                       "ababab";

        DayTwo dayTwo = new DayTwo ( );

        assertEqual ( 12, dayTwo.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "abcde\n" +
                       "fghij\n" +
                       "klmno\n" +
                       "pqrst\n" +
                       "fguij\n" +
                       "axcye\n" +
                       "wvxyz";

        DayTwo dayTwo = new DayTwo ( );

        assertEqual ( "fgij", dayTwo.partTwo ( input ) );
    }
}
