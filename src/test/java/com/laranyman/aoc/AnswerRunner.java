package com.laranyman.aoc;

import com.google.common.collect.Lists;
import com.laranyman.aoc.eighteen.dayfifteen.DayFifteen;
import com.laranyman.aoc.eighteen.dayfour.DayFour;
import com.laranyman.aoc.eighteen.dayfourteen.DayFourteen;
import com.laranyman.aoc.eighteen.daysixteen.DaySixteen;
import com.laranyman.aoc.eighteen.daythirteen.DayThirteen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lara
 */
public class AnswerRunner
{
    private static final Logger s_logger = LoggerFactory.getLogger ( AnswerRunner.class );

    private static final String s_printLineFormat = "%10s %30s %30s";

    private final Map< DayIfc, List< long[] > > m_days;

    public AnswerRunner ( )
    {
        m_days = new LinkedHashMap<> ( );

        List< long[] > daySix = emptyParameters ( );
        daySix.add ( 1, new long[] { 10000 } );

        List< long[] > daySeven = emptyParameters ( );
        daySix.add ( 1, new long[] { 5, 60 } );

        List< long[] > dayNine = Lists.newArrayList ( );
        dayNine.add ( new long[] { 459, 71790 } );
        dayNine.add ( new long[] { 459, 7179000 } );

        List< long[] > dayTwelve = Lists.newArrayList ( );
        dayTwelve.add ( new long[] { 20 } );
        dayTwelve.add ( new long[] { 50000000000L } );

        List< long[] > dayFifteen = emptyParameters ( );
        dayFifteen.add ( 1, new long[] { 15 } );

//        m_days.put ( new DayOne ( ), emptyParameters ( ) );
//        m_days.put ( new DayTwo ( ), emptyParameters ( ) );
//        m_days.put ( new DayThree ( ), emptyParameters ( ) );
//        m_days.put ( new DayFour ( ), emptyParameters ( ) );
//        m_days.put ( new DayFive ( ), emptyParameters ( ) );
//        m_days.put ( new DaySix ( ), daySix );
//        m_days.put ( new DaySeven ( ), daySeven );
//        m_days.put ( new DayEight ( ), emptyParameters ( ) );
//        m_days.put ( new DayNine ( ), dayNine );
//        m_days.put ( new DayTen ( ), emptyParameters ( ) );
//        m_days.put ( new DayEleven ( ), emptyParameters ( ) );
//        m_days.put ( new DayTwelve ( ), dayTwelve );
//        m_days.put ( new DayThirteen ( ), emptyParameters () );
//        m_days.put ( new DayFourteen ( ), emptyParameters () );
        m_days.put ( new DayFifteen ( ), dayFifteen );
//        m_days.put ( new DaySixteen ( ), emptyParameters ( ) );
    }

    public static void main ( String[] args ) throws IOException
    {
        AnswerRunner answerRunner = new AnswerRunner ( );
        answerRunner.run ( );
    }

    private void run ( ) throws IOException
    {
        s_logger.info ( String.format ( s_printLineFormat, "Day", "PartOne", "PartTwo" ) );

        for ( Map.Entry< DayIfc, List< long[] > > day : m_days.entrySet ( ) )
        {
            final DayIfc dayInstance = day.getKey ( );

            final String dayNumber = dayInstance.getClass ( ).getSimpleName ( );

            final URL input = AnswerRunner.class.getResource (
                    "/eighteen/" + dayNumber + "Input.txt" );

            InputStream inStream = new FileInputStream ( new File ( input.getFile ( ) ) );

            final String inputString = read ( inStream );

            final String partOneAnswer = day.getValue ( ).get ( 0 ).length == 0
                    ? dayInstance.partOne ( inputString )
                    : inputString.equals ( "" )
                    ? dayInstance.partOne ( day.getValue ( ).get ( 0 ) )
                    : dayInstance.partOne ( inputString, day.getValue ( ).get ( 0 ) );

            final String partTwoAnswer = day.getValue ( ).get ( 1 ).length == 0
                    ? dayInstance.partTwo ( inputString )
                    : inputString.equals ( "" )
                    ? dayInstance.partTwo ( day.getValue ( ).get ( 1 ) )
                    : dayInstance.partTwo ( inputString, day.getValue ( ).get ( 1 ) );

            s_logger.info ( String.format ( s_printLineFormat, dayNumber, partOneAnswer, partTwoAnswer ) );
        }
    }

    private static String read ( InputStream input ) throws IOException
    {
        try ( BufferedReader buffer = new BufferedReader ( new InputStreamReader ( input ) ) )
        {
            return buffer.lines ( ).collect ( Collectors.joining ( "\n" ) );
        }
    }

    private static List< long[] > emptyParameters ( )
    {
        List< long[] > day = Lists.newArrayList ( );
        day.add ( new long[] { } );
        day.add ( new long[] { } );
        return day;
    }
}
