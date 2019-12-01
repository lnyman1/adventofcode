package com.laranyman.aoc.eighteen.daytwelve;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayTwelveTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "#..#.#..##......###...###\n" +
                       "...## => #\n" +
                       "..#.. => #\n" +
                       ".#... => #\n" +
                       ".#.#. => #\n" +
                       ".#.## => #\n" +
                       ".##.. => #\n" +
                       ".#### => #\n" +
                       "#.#.# => #\n" +
                       "#.### => #\n" +
                       "##.#. => #\n" +
                       "##.## => #\n" +
                       "###.. => #\n" +
                       "###.# => #\n" +
                       "####. => #";

        DayTwelve dayTwelve = new DayTwelve ( );
        final String output = dayTwelve.partOne ( input, 20 );

        assertEqual ( 325, output );
    }
}
