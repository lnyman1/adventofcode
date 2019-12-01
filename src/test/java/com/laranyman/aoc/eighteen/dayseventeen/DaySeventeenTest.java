package com.laranyman.aoc.eighteen.dayseventeen;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DaySeventeenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "x=495, y=2..7\n" +
                       "y=7, x=495..501\n" +
                       "x=501, y=3..7\n" +
                       "x=498, y=2..4\n" +
                       "x=506, y=1..2\n" +
                       "x=498, y=10..13\n" +
                       "x=504, y=10..13\n" +
                       "y=13, x=498..504\n";
        DaySeventeen daySeventeen = new DaySeventeen ( );
        assertEqual ( 57, daySeventeen.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "x=495, y=2..7\n" +
                       "y=7, x=495..501\n" +
                       "x=501, y=3..7\n" +
                       "x=498, y=2..4\n" +
                       "x=506, y=1..2\n" +
                       "x=498, y=10..13\n" +
                       "x=504, y=10..13\n" +
                       "y=13, x=498..504\n";
        DaySeventeen daySeventeen = new DaySeventeen ( );
        assertEqual ( 29, daySeventeen.partTwo ( input ) );
    }
}
