package com.laranyman.aoc.eighteen.dayten;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.util.AdventOfCodeUtil;
import com.laranyman.aoc.util.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lara
 */
public class DayTen implements DayIfc
{
    public static final Logger LOGGER = LoggerFactory.getLogger ( DayTen.class );

    @Override
    public String partOne ( final String input )
    {
        final Map< Coordinate, Coordinate > coordinates = parseInput ( input );

        List< List< Point > > result = Lists.newArrayList ( );

        for ( int i = 0; i < 25000; i++ )
        {
            List< Point > intermediate = Lists.newArrayList ( );

            for ( Map.Entry< Coordinate, Coordinate > entry : coordinates.entrySet ( ) )
            {
                intermediate.add ( new Point ( move ( entry.getKey ( ), entry.getValue ( ), i ), entry.getValue ( ),
                        i ) );
            }

            result.add ( intermediate );
        }

        final List< Point > smallestRange = result.stream ( )
                .sorted ( Comparator.comparing ( DayTen::calculateRange ) )
                .collect ( Collectors.toList ( ) )
                .get ( 0 );

        final Integer maxX = smallestRange
                .stream ( )
                .map ( e -> e.m_coordinate.getxCoordinate ( ) )
                .max ( Integer::compareTo ).get ( );

        final Integer maxY = smallestRange
                .stream ( )
                .map ( e -> e.m_coordinate.getyCoordinate ( ) )
                .max ( Integer::compareTo ).get ( );

        int[][] grid = new int[ maxX + 1 ][ maxY + 1 ];

        final List< Coordinate > coords = smallestRange.stream ( )
                .map ( e -> e.m_coordinate )
                .collect ( Collectors.toList ( ) );

        for ( int i = 0; i <= maxX; i++ )
        {
            for ( int j = 0; j <= maxY; j++ )
            {
                grid[ i ][ j ] = 0;
            }
        }

        for ( Coordinate coordinate : coords )
        {
            grid[ coordinate.getxCoordinate ( ) ][ coordinate.getyCoordinate ( ) ] = 1;
        }

        printGrid ( grid, maxX, maxY );

        return null;
    }

    @Override
    public String partTwo ( final String input )
    {
        final Map< Coordinate, Coordinate > coordinates = parseInput ( input );

        List< List< Point > > result = Lists.newArrayList ( );

        for ( int i = 0; i < 50000; i++ )
        {
            List< Point > intermediate = Lists.newArrayList ( );

            for ( Map.Entry< Coordinate, Coordinate > entry : coordinates.entrySet ( ) )
            {
                intermediate.add ( new Point ( move ( entry.getKey ( ), entry.getValue ( ), i ), entry.getValue ( ),
                        i ) );
            }

            result.add ( intermediate );
        }

        final List< Point > smallestRange = result.stream ( )
                .sorted ( Comparator.comparing ( DayTen::calculateRange ) )
                .collect ( Collectors.toList ( ) )
                .get ( 0 );

        return String.valueOf ( smallestRange.get ( 0 ).m_second );
    }

    private static Coordinate move (
            final Coordinate coordinate,
            final Coordinate velocity,
            final int moveFactor )
    {
        final int x = coordinate.getxCoordinate ( ) + velocity.getxCoordinate ( ) * moveFactor;
        final int y = coordinate.getyCoordinate ( ) + velocity.getyCoordinate ( ) * moveFactor;

        return new Coordinate ( x, y );
    }

    private static int calculateRange ( final List< Point > coordinates )
    {
        final Integer maxX = coordinates
                .stream ( )
                .map ( e -> e.m_coordinate.getxCoordinate ( ) )
                .max ( Integer::compareTo ).get ( );

        final Integer maxY = coordinates
                .stream ( )
                .map ( e -> e.m_coordinate.getyCoordinate ( ) )
                .max ( Integer::compareTo ).get ( );

        final Integer minX = coordinates
                .stream ( )
                .map ( e -> e.m_coordinate.getxCoordinate ( ) )
                .min ( Integer::compareTo ).get ( );

        final Integer minY = coordinates
                .stream ( )
                .map ( e -> e.m_coordinate.getyCoordinate ( ) )
                .min ( Integer::compareTo ).get ( );

        return maxX - minX + maxY - minY;
    }

    private static void printGrid (
            final int[][] grid,
            final int maxX,
            final int maxY )
    {
        for ( int i = 0; i <= maxY; i++ )
        {
            String line = "";

            for ( int j = 0; j <= maxX; j++ )
            {
                line += grid[ j ][ i ] == 1 ? "#" : ".";
            }

            LOGGER.info ( line );
        }
    }

    private static Map< Coordinate, Coordinate > parseInput ( final String input )
    {
        Map< Coordinate, Coordinate > results = Maps.newHashMap ( );

        final String[] lines = AdventOfCodeUtil.splitInput ( input );
        for ( String line : lines )
        {
            final String[] numbers = line.split ( "<" );
            final String[] numbersTwo = numbers[ 1 ].split ( ">" );
            final String[] coord = numbersTwo[ 0 ].split ( "," );
            final String[] velocity = numbers[ 2 ].split ( "," );

            results.put ( new Coordinate (
                            Integer.parseInt ( coord[ 0 ].trim ( ) ),
                            Integer.parseInt ( coord[ 1 ].trim ( ) ) ),
                    new Coordinate (
                            Integer.parseInt ( velocity[ 0 ].trim ( ) ),
                            Integer.parseInt ( velocity[ 1 ].substring ( 0, velocity[ 1 ].length ( ) - 1 ).trim ( ) )
                    ) );
        }

        return results;
    }

    private static final class Point
    {
        private final Coordinate m_coordinate;

        private final Coordinate m_velocity;

        private final int m_second;

        public Point ( final Coordinate coordinate, final Coordinate velocity, final int second )
        {
            m_coordinate = coordinate;
            m_velocity = velocity;
            m_second = second;
        }
    }
}
