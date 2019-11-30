package com.laranyman.aoc.eighteen.dayfourteen;

import com.laranyman.aoc.template.DayN;
import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;
import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DayFourteenTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEquals ( "5158916779", dayFourteen.partOne ( String.valueOf ( 9 ) ) );
    }

    @Test
    public void testPartOneExampleTwo ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEquals ( "0124515891", dayFourteen.partOne ( String.valueOf ( 5 ) ) );
    }

    @Test
    public void testPartOneExampleThree ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEquals ( "9251071085", dayFourteen.partOne ( String.valueOf ( 18 ) ) );
    }

    @Test
    public void testPartOneExampleFour ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEquals ( "5941429882", dayFourteen.partOne ( String.valueOf ( 2018 ) ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEqual ( 9, dayFourteen.partTwo ( "51589" ) );
    }

    @Test
    public void testPartTwoExampleTwo ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEqual ( 5, dayFourteen.partTwo ( "01245" ) );
    }

    @Test
    public void testPartTwoExampleThree ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEqual ( 18, dayFourteen.partTwo ( "92510" ) );
    }

    @Test
    public void testPartTwoExampleFour ( )
    {
        DayFourteen dayFourteen = new DayFourteen ( );
        assertEqual ( 2018, dayFourteen.partTwo ( "59414" ) );
    }
}
