package com.laranyman.aoc.eighteen.daynineteen;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayNineteenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "seti 5 0 1\n" +
                       "seti 6 0 2\n" +
                       "addi 0 1 0\n" +
                       "addr 1 2 3\n" +
                       "setr 1 0 0\n" +
                       "seti 8 0 4\n" +
                       "seti 9 0 5";

        DayNineteen dayNineteen = new DayNineteen ( );
        assertEqual ( 6, dayNineteen.partOne ( input, 0 ) );
    }
}
