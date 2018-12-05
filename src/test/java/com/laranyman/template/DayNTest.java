package com.laranyman.template;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayNTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "";
        assertEquals(3, DayN.partOne ( input ));
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "";
        assertEquals(3, DayN.partTwo (input));
    }

    @Test
    public void testPartOneActual ( )
    {
        String input = "";
        System.out.println ( "Part One Answer" + DayN.partOne (input));
    }

    @Test
    public void testPartTwoActual ( )
    {
        String input = "";
        System.out.println ( "Part Two Answer" + DayN.partTwo (input));
    }
}
