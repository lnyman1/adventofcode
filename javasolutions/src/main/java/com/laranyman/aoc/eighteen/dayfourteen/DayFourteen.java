package com.laranyman.aoc.eighteen.dayfourteen;

import com.google.common.collect.Lists;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Lara
 */
public class DayFourteen implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayFourteen.class );

    @Override
    public String partOne ( final String input )
    {
        List< Integer > scores = Lists.newArrayList ( );
        scores.add ( 3 );
        scores.add ( 7 );
        int[] elfScores = new int[] { 3, 7, 0, 1 };

        int count = 0;

        final int duration = Integer.parseInt ( input );

        while ( count < duration + 10 )
        {
            elfScores = cycle ( scores, elfScores[ 0 ], elfScores[ 1 ], elfScores[ 2 ], elfScores[ 3 ] );
            count++;
        }

        String result = "";

        final String value = scores.stream ( )
                .map ( String::valueOf )
                .collect ( Collectors.joining ( ) );

        for ( int i = 0; i < 10; i++ )
        {
            result += String.valueOf ( value.charAt ( duration + i ) );
        }

        return result;

    }

    @Override
    public String partTwo ( final String input )
    {
        List< Integer > scores = Lists.newArrayList ( );
        scores.add ( 3 );
        scores.add ( 7 );

        int[] elfScores = new int[] { 3, 7, 0, 1 };

        int count = 0;

        while ( true )
        {
            elfScores = cycle ( scores, elfScores[ 0 ], elfScores[ 1 ], elfScores[ 2 ], elfScores[ 3 ] );

            int begin = 0;

            if ( scores.size ( ) > 7 )
            {
                begin = scores.size ( ) - 7;
            }

            final String value = scores.subList ( begin, scores.size ( ) ).stream ( )
                    .map ( String::valueOf )
                    .collect ( Collectors.joining ( ) );

            final int idx = value.indexOf ( input );

            if ( idx != -1 )
            {
                final String result = scores.stream ( )
                        .map ( String::valueOf )
                        .collect ( Collectors.joining ( ) );

                return String.valueOf ( result.indexOf ( input ) );
            }

            count++;

            if ( count % 1000 == 0 )
            {
                LOGGER.info ( String.valueOf ( count ) );
            }
        }
    }

    private static int[] cycle (
            final List< Integer > scores,
            final int elfOneScore,
            final int elfTwoScore,
            final int elfOneIdx,
            final int elfTwoIdx )
    {

        final int sum = elfOneScore + elfTwoScore;

        final String sumString = String.valueOf ( sum );

        int[] newRecipes;

        if ( sumString.length ( ) == 1 )
        {
            newRecipes = new int[] { sum };
        }
        else if ( sumString.length ( ) == 2 )
        {
            newRecipes = Arrays.stream ( sumString.split ( "" ) ).mapToInt ( Integer::parseInt ).toArray ( );
        }
        else
        {
            throw new AdventOfCodeException ( String.format ( "This case has not been handled : %s",
                    sumString.length ( ) ) );
        }

        for ( int i = 0; i < newRecipes.length; i++ )
        {
            scores.add ( newRecipes[ i ] );
        }

        int elfOneIndex = ( elfOneIdx + elfOneScore + 1 ) % scores.size ( );
        int elfTwoIndex = ( elfTwoIdx + elfTwoScore + 1 ) % scores.size ( );

        final int elfOne = scores.get ( elfOneIndex );
        final int elfTwo = scores.get ( elfTwoIndex );

        //LOGGER.info ( scores.stream ( ).map ( String::valueOf ).collect ( Collectors.joining ( ) ) );

        return new int[] { elfOne, elfTwo, elfOneIndex, elfTwoIndex };

    }
}
