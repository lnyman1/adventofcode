package com.laranyman.eighteen.daysix;

import com.google.common.collect.Maps;
import com.laranyman.eighteen.dayfour.Guard;
import com.laranyman.util.AdventOfCodeUtil;
import com.laranyman.util.Coordinate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.laranyman.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DaySix
{
    public static int partOne ( final String input )
    {
        final Coordinate[] coordinates = parseInput ( input );

        Map< Integer, Coordinate > coordinateMap = IntStream.range ( 0, coordinates.length )
                .boxed ( )
                .collect ( Collectors.toMap ( Function.identity ( ), i -> coordinates[ i ], ( a, b ) -> b ) );

        final Integer maxX = coordinateMap.values ( )
                .stream ( )
                .map ( Coordinate::getxCoordinate )
                .max ( Integer::compareTo ).get ( );

        final Integer maxY = coordinateMap.values ( )
                .stream ( )
                .map ( Coordinate::getyCoordinate )
                .max ( Integer::compareTo ).get ( );


        int[][] grid = new int[ maxX + 1 ][ maxY + 1 ];

        Map< Integer, Integer > regions = Maps.newHashMap ( );

        for ( int x = 0; x <= maxX; x++ )
        {
            for ( int y = 0; y <= maxY; y++ )
            {
                int furthestAway = maxX + maxY;
                int closest = -1;

                for ( int i = 0; i < coordinateMap.size ( ); i++ )
                {
                    int dist = calculateManhattenDistance ( x, y, coordinateMap.get ( i ) );
                    if ( dist < furthestAway )
                    {
                        furthestAway = dist;
                        closest = i;
                    }
                    else if ( dist == furthestAway )
                    {
                        closest = -1;
                    }
                }

                grid[ x ][ y ] = closest;

                regions.computeIfPresent ( closest, ( k, v ) -> v + 1 );
                regions.putIfAbsent ( closest, 1 );
            }
        }

        for ( int x = 0; x <= maxX; x++ )
        {
            regions.remove ( grid[ x ][ 0 ] );
            regions.remove ( grid[ x ][ maxY ] );
        }
        for ( int y = 0; y <= maxY; y++ )
        {
            regions.remove ( grid[ 0 ][ y ] );
            regions.remove ( grid[ maxX ][ y ] );
        }

        return regions.values ( )
                .stream ( )
                .max ( Comparator.naturalOrder ( ) )
                .get ( );
    }

    public static int partTwo ( final String input, final int magicNumber )
    {
        final Coordinate[] coordinates = parseInput ( input );

        Map< Integer, Coordinate > coordinateMap = IntStream.range ( 0, coordinates.length )
                .boxed ( )
                .collect ( Collectors.toMap ( Function.identity ( ), i -> coordinates[ i ], ( a, b ) -> b ) );

        final Integer maxX = coordinateMap.values ( )
                .stream ( )
                .map ( Coordinate::getxCoordinate )
                .max ( Integer::compareTo ).get ( );

        final Integer maxY = coordinateMap.values ( )
                .stream ( )
                .map ( Coordinate::getyCoordinate )
                .max ( Integer::compareTo ).get ( );


        int[][] grid = new int[ maxX + 1 ][ maxY + 1 ];

        Map< Integer, Integer > regions = Maps.newHashMap ( );

        for ( int x = 0; x <= maxX; x++ )
        {
            for ( int y = 0; y <= maxY; y++ )
            {
                int furthestAway = maxX + maxY;
                int closest = -1;

                for ( int i = 0; i < coordinateMap.size ( ); i++ )
                {
                    int dist = calculateManhattenDistance ( x, y, coordinateMap.get ( i ) );
                    if ( dist < furthestAway )
                    {
                        furthestAway = dist;
                        closest = i;
                    }
                    else if ( dist == furthestAway )
                    {
                        closest = -1;
                    }
                }

                grid[ x ][ y ] = closest;

                regions.computeIfPresent ( closest, ( k, v ) -> v + 1 );
                regions.putIfAbsent ( closest, 1 );

            }
        }

        for ( int x = 0; x <= maxX; x++ )
        {
            regions.remove ( grid[ x ][ 0 ] );
            regions.remove ( grid[ x ][ maxY ] );
        }
        for ( int y = 0; y <= maxY; y++ )
        {
            regions.remove ( grid[ 0 ][ y ] );
            regions.remove ( grid[ maxX ][ y ] );
        }

        int region = 0;

        for ( int x = 0; x <= maxX; x++ )
        {
            for ( int y = 0; y <= maxY; y++ )
            {
                int totalDist = 0;

                for ( int i = 0; i < coordinateMap.size ( ); i++ )
                {
                    int distForCoord = calculateManhattenDistance ( x, y, coordinateMap.get ( i ) );
                    totalDist += distForCoord;
                }

                if ( totalDist < magicNumber )
                {
                    region++;
                }
            }
        }

        return region;
    }


    private static int calculateManhattenDistance (
            final int x,
            final int y,
            final Coordinate coordinate )
    {
        return Math.abs ( x - coordinate.getxCoordinate ( ) )
               + Math.abs ( y - coordinate.getyCoordinate ( ) );
    }

    private static Coordinate[] parseInput ( final String input )
    {
        return Arrays.stream ( splitInput ( input ) )
                .map ( e -> e.split ( ", " ) )
                .map ( e -> new Coordinate ( Integer.parseInt ( e[ 0 ] ), Integer.parseInt ( e[ 1 ] ) ) )
                .toArray ( Coordinate[]::new );
    }
}
