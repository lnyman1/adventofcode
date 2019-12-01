package com.laranyman.aoc.eighteen.dayeighteen;

import com.laranyman.aoc.template.DayN;
import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayEighteenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = ".#.#...|#.\n" +
                       ".....#|##|\n" +
                       ".|..|...#.\n" +
                       "..|#.....#\n" +
                       "#.#|||#|#|\n" +
                       "...#.||...\n" +
                       ".|....|...\n" +
                       "||...#|.#|\n" +
                       "|.||||..|.\n" +
                       "...#.|..|.";
        DayEighteen dayEighteen = new DayEighteen ( );
        assertEqual ( 1147, dayEighteen.partOne ( input ) );
    }
}
