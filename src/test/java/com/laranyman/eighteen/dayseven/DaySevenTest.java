package com.laranyman.eighteen.dayseven;

import org.junit.Test;

import static com.laranyman.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DaySevenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "Step C must be finished before step A can begin.\n" +
                       "Step C must be finished before step F can begin.\n" +
                       "Step A must be finished before step B can begin.\n" +
                       "Step A must be finished before step D can begin.\n" +
                       "Step B must be finished before step E can begin.\n" +
                       "Step D must be finished before step E can begin.\n" +
                       "Step F must be finished before step E can begin.\n";

        DaySeven daySeven = new DaySeven ( );

        assertEqual ( "CABDFE", daySeven.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "Step C must be finished before step A can begin.\n" +
                       "Step C must be finished before step F can begin.\n" +
                       "Step A must be finished before step B can begin.\n" +
                       "Step A must be finished before step D can begin.\n" +
                       "Step B must be finished before step E can begin.\n" +
                       "Step D must be finished before step E can begin.\n" +
                       "Step F must be finished before step E can begin.";

        DaySeven daySeven = new DaySeven ( );

        assertEqual ( 15, daySeven.partTwo ( input, 2, 0 ) );
    }
}
