package com.laranyman.eighteen.daynine;

import com.laranyman.eighteen.daynine.DayNine;
import org.junit.Test;

import static com.laranyman.eighteen.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayNineTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "";
        DayNine dayNine = new DayNine ();
        assertEqual(3, dayNine.partOne ( input ));
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "";
        DayNine dayNine = new DayNine ();
        assertEqual(3, dayNine.partTwo (input));
    }
}
