package com.laranyman.aoc.eighteen.dayfifteen;

import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayFifteenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "#######\n" +
                       "#.G...#\n" +
                       "#...EG#\n" +
                       "#.#.#G#\n" +
                       "#..G#E#\n" +
                       "#.....#\n" +
                       "#######";
        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 27730, dayFifteen.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        String input = "#######\n" +
                       "#G..#E#\n" +
                       "#E#E.E#\n" +
                       "#G.##.#\n" +
                       "#...#E#\n" +
                       "#...E.#\n" +
                       "#######";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 36334, dayFifteen.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleThree ( )
    {
        String input = "#######\n" +
                       "#E.G#.#\n" +
                       "#.#G..#\n" +
                       "#G.#.G#\n" +
                       "#G..#.#\n" +
                       "#...E.#\n" +
                       "#######";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 27755, dayFifteen.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFour ( )
    {
        String input = "#######\n" +
                       "#.E...#\n" +
                       "#.#..G#\n" +
                       "#.###.#\n" +
                       "#E#G#G#\n" +
                       "#...#G#\n" +
                       "#######";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 28944, dayFifteen.partOne ( input ) );
    }

    @Test
    public void testPartOneExampleFive ( )
    {
        String input = "#########\n" +
                       "#G......#\n" +
                       "#.E.#...#\n" +
                       "#..##..G#\n" +
                       "#...##..#\n" +
                       "#...#...#\n" +
                       "#.G...G.#\n" +
                       "#.....G.#\n" +
                       "#########";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 18740, dayFifteen.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "#######\n" +
                       "#.G...#\n" +
                       "#...EG#\n" +
                       "#.#.#G#\n" +
                       "#..G#E#\n" +
                       "#.....#\n" +
                       "#######";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 4988, dayFifteen.partTwo ( input, 15 ) );
    }

    @Test
    public void testPartTwoExampleTwo ( )
    {
        String input = "#######\n" +
                       "#E..EG#\n" +
                       "#.#G.E#\n" +
                       "#E.##E#\n" +
                       "#G..#.#\n" +
                       "#..E#.#\n" +
                       "#######";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 31284, dayFifteen.partTwo ( input, 4 ) );
    }

    @Test
    public void testPartTwoExampleThree ( )
    {
        String input = "#######\n" +
                       "#E.G#.#\n" +
                       "#.#G..#\n" +
                       "#G.#.G#\n" +
                       "#G..#.#\n" +
                       "#...E.#\n" +
                       "#######";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 3478, dayFifteen.partTwo ( input, 15 ) );
    }

    @Test
    public void testPartTwoExampleFour ( )
    {
        String input = "#######\n" +
                       "#.E...#\n" +
                       "#.#..G#\n" +
                       "#.###.#\n" +
                       "#E#G#G#\n" +
                       "#...#G#\n" +
                       "#######";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 6474, dayFifteen.partTwo ( input, 12 ) );
    }

    @Test
    public void testPartTwoExampleFive ( )
    {
        String input = "#########\n" +
                       "#G......#\n" +
                       "#.E.#...#\n" +
                       "#..##..G#\n" +
                       "#...##..#\n" +
                       "#...#...#\n" +
                       "#.G...G.#\n" +
                       "#.....G.#\n" +
                       "#########";

        DayFifteen dayFifteen = new DayFifteen ( );
        assertEqual ( 1140, dayFifteen.partTwo ( input, 34 ) );
    }
}
