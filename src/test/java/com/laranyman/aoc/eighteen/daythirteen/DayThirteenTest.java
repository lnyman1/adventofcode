package com.laranyman.aoc.eighteen.daythirteen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayThirteenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "/->-\\        \n" +
                       "|   |  /----\\\n" +
                       "| /-+--+-\\  |\n" +
                       "| | |  | v  |\n" +
                       "\\-+-/  \\-+--/\n" +
                       "  \\------/   ";
        DayThirteen dayThirteen = new DayThirteen ( );
        assertEquals ( "7,3", dayThirteen.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "/>-<\\  \n" +
                       "|   |  \n" +
                       "| /<+-\\\n" +
                       "| | | v\n" +
                       "\\>+</ |\n" +
                       "  |   ^\n" +
                       "  \\<->/";
        DayThirteen dayThirteen = new DayThirteen ( );
        assertEquals ( "6,4", dayThirteen.partTwo ( input ) );
    }
}
