package com.laranyman.aoc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lara
 */
public final class AdventOfCodeUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( AdventOfCodeUtil.class );

    public static String[] splitInput ( final String input )
    {
        return input.split ( "\n" );
    }

    public static int mode ( final List< Integer > a )
    {
        int maxValue = 0;
        int maxCount = 0;

        for ( int i = 0; i < a.size ( ); ++i )
        {
            int count = 0;
            for ( int integer : a )
            {
                if ( integer == a.get ( i ) )
                {
                    ++count;
                }
            }
            if ( count > maxCount )
            {
                maxCount = count;
                maxValue = a.get ( i );
            }
        }

        return maxValue;
    }

    public static void printGrid (
            final int[][] grid )
    {
        for ( int i = 0; i <= grid.length; i++ )
        {
            String line = "";

            for ( int j = 0; j <= grid[ 0 ].length; j++ )
            {
                line += grid[ j ][ i ] == 1 ? "#" : ".";
            }

            LOGGER.info ( line );
        }
    }

    public static <T> void rotate (
            final LinkedList< T > list,
            final int number )
    {
        if ( number == 0 )
        {
            return;
        }

        if ( number > 0 )
        {
            int count = 0;
            while ( count < number )
            {
                final T last = list.removeLast ( );
                list.addFirst ( last );
                count++;
            }

            return;
        }

        int count = 0;
        while ( count < Math.abs ( number ) )
        {
            final T first = list.removeFirst ( );
            list.addLast ( first );
            count++;
        }

        return;
    }

}
