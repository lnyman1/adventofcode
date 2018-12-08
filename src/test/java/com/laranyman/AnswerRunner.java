package com.laranyman;

import com.laranyman.eighteen.DayIfc;
import com.laranyman.eighteen.dayeight.DayEight;
import com.laranyman.eighteen.dayfive.DayFive;
import com.laranyman.eighteen.dayfour.DayFour;
import com.laranyman.eighteen.dayone.DayOne;
import com.laranyman.eighteen.dayseven.DaySeven;
import com.laranyman.eighteen.daysix.DaySix;
import com.laranyman.eighteen.daythree.DayThree;
import com.laranyman.eighteen.daytwo.DayTwo;
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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lara
 */
public class AnswerRunner
{
    private static final Logger s_logger = LoggerFactory.getLogger ( AnswerRunner.class );

    private static final String s_printLineFormat = "%10s %30s %30s";

    private final Map< DayIfc, int[] > m_days;

    public AnswerRunner ( )
    {
        m_days = new LinkedHashMap<> ( );
        m_days.put ( new DayOne ( ), emptyParameters ( ) );
        m_days.put ( new DayTwo ( ), emptyParameters ( ) );
        m_days.put ( new DayThree ( ), emptyParameters ( ) );
        m_days.put ( new DayFour ( ), emptyParameters ( ) );
        m_days.put ( new DayFive ( ), emptyParameters ( ) );
        m_days.put ( new DaySix ( ), new int[] { 10000 } );
        m_days.put ( new DaySeven ( ), new int[] { 5, 60 } );
        m_days.put ( new DayEight ( ), emptyParameters ( ) );
//        m_days.put ( new DayNine ( ), emptyParameters ( ) );
    }

    public static void main ( String[] args ) throws IOException
    {
        AnswerRunner answerRunner = new AnswerRunner ( );
        answerRunner.run ( );
    }

    private void run ( ) throws IOException
    {
        s_logger.info ( String.format ( s_printLineFormat, "Day", "PartOne", "PartTwo" ) );

        for ( Map.Entry< DayIfc, int[] > day : m_days.entrySet ( ) )
        {
            final DayIfc dayInstance = day.getKey ( );

            final String dayNumber = dayInstance.getClass ( ).getSimpleName ( );

            final URL input = AnswerRunner.class.getResource (
                    "/" + dayNumber + "Input.txt" );

            InputStream inStream = new FileInputStream ( new File ( input.getFile ( ) ) );

            final String inputString = read ( inStream );

            final String partOneAnswer = dayInstance.partOne ( inputString );

            final String partTwoAnswer = day.getValue ( ).length == 0
                    ? dayInstance.partTwo ( inputString )
                    : dayInstance.partTwo ( inputString, day.getValue ( ) );

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

    private static int[] emptyParameters ( )
    {
        return new int[] { };
    }
}
