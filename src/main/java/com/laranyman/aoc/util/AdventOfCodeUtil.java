package com.laranyman.aoc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
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
                line += grid[ j ][ i ] == 1 ? "#" : "src/test/java";
            }

            LOGGER.info ( line );
        }
    }

    public static < T > void rotate (
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

    public static int[] execute (
            final OpCode opCode,
            final int[] instructions,
            final int[] beforeRegister )
    {
        final int a = instructions[ 1 ];
        final int b = instructions[ 2 ];
        final int c = instructions[ 3 ];

        final int[] afterRegister = Arrays.copyOf ( beforeRegister, beforeRegister.length );

        switch ( opCode )
        {
            case addr:
                afterRegister[ c ] = beforeRegister[ a ] + beforeRegister[ b ];
                break;
            case addi:
                afterRegister[ c ] = beforeRegister[ a ] + b;
                break;
            case mulr:
                afterRegister[ c ] = beforeRegister[ a ] * beforeRegister[ b ];
                break;
            case muli:
                afterRegister[ c ] = beforeRegister[ a ] * b;
                break;
            case banr:
                afterRegister[ c ] = beforeRegister[ a ] & beforeRegister[ b ];
                break;
            case bani:
                afterRegister[ c ] = beforeRegister[ a ] & b;
                break;
            case borr:
                afterRegister[ c ] = beforeRegister[ a ] | beforeRegister[ b ];
                break;
            case bori:
                afterRegister[ c ] = beforeRegister[ a ] | b;
                break;
            case setr:
                afterRegister[ c ] = beforeRegister[ a ];
                break;
            case seti:
                afterRegister[ c ] = a;
                break;
            case gtir:
                afterRegister[ c ] = a > beforeRegister[ b ] ? 1 : 0;
                break;
            case gtri:
                afterRegister[ c ] = beforeRegister[ a ] > b ? 1 : 0;
                break;
            case gtrr:
                afterRegister[ c ] = beforeRegister[ a ] > beforeRegister[ b ] ? 1 : 0;
                break;
            case eqir:
                afterRegister[ c ] = a == beforeRegister[ b ] ? 1 : 0;
                break;
            case eqri:
                afterRegister[ c ] = beforeRegister[ a ] == b ? 1 : 0;
                break;
            case eqrr:
                afterRegister[ c ] = beforeRegister[ a ] == beforeRegister[ b ] ? 1 : 0;
                break;
        }
        return afterRegister;
    }

    public static int calculateManhattenDistance (
            final int x,
            final int y,
            final Coordinate coordinate )
    {
        return Math.abs ( x - coordinate.getxCoordinate ( ) )
               + Math.abs ( y - coordinate.getyCoordinate ( ) );
    }
}
