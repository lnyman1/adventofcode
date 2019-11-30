package com.laranyman.aoc.eighteen.dayeleven;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayElevenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "18";
        DayEleven dayEleven = new DayEleven ( );
        assertEquals ( "33,45", dayEleven.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "42";
        DayEleven dayEleven = new DayEleven ( );
        assertEquals ( "21,61", dayEleven.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "18";
        DayEleven dayEleven = new DayEleven ( );
        assertEquals ( "90,269,16", dayEleven.partTwo ( input ) );
    }

    @Test
    public void testPartTwoExampleTwo ( )
    {
        String input = "42";
        DayEleven dayEleven = new DayEleven ( );
        assertEquals ( "232,251,12", dayEleven.partTwo ( input ) );
    }
}
