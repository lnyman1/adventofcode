package com.laranyman.aoc.eighteen.daytwenty;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayTwentyTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "^WNE$";
        DayTwenty dayTwenty = new DayTwenty ( );
        assertEqual ( 3, dayTwenty.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "^ENWWW(NEEE|SSE(EE|N))$";
        DayTwenty dayTwenty = new DayTwenty ( );
        assertEqual ( 10, dayTwenty.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleThree ( )
    {
        String input = "(^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$)";
        DayTwenty dayTwenty = new DayTwenty ( );
        assertEqual ( 18, dayTwenty.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFour ( )
    {
        String input = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
        DayTwenty dayTwenty = new DayTwenty ( );
        assertEqual ( 23, dayTwenty.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFive ( )
    {
        String input = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";
        DayTwenty dayTwenty = new DayTwenty ( );
        assertEqual ( 31, dayTwenty.partOne ( input ) );
    }
}
