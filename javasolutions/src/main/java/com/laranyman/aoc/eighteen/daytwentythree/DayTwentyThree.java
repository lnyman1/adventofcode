package com.laranyman.aoc.eighteen.daytwentythree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.util.Coordinate;
import com.laranyman.aoc.util.Coordinate3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayTwentyThree implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayTwentyThree.class );

    @Override
    public String partOne ( final String input )
    {
        final List< Nanobot > nanobots = parseInput ( input );

        final List< Nanobot > sorted = nanobots.stream ( )
                .sorted ( Comparator.comparingInt ( Nanobot::getRadius ) )
                .collect ( Collectors.toList ( ) );

        final Nanobot highestRadius = sorted.get ( sorted.size ( ) - 1 );

        int count = 0;

        for ( Nanobot nanobot : nanobots )
        {
            if ( highestRadius.getCoordinate ( ).manhattenDistance ( nanobot.getCoordinate ( ) ) <= highestRadius.getRadius ( ) )
            {
                count++;
            }
        }

        return String.valueOf ( count );
    }

    @Override
    public String partTwo ( final String input )
    {
        final List< Nanobot > nanobots = parseInput ( input );

        List< Coordinate3D > sortedX = nanobots.stream ( )
                .map ( Nanobot::getCoordinate )
                .sorted ( Comparator.comparingInt ( Coordinate3D::getxCoordinate ) )
                .collect ( Collectors.toList ( ) );

        List< Coordinate3D > sortedY = nanobots.stream ( )
                .map ( Nanobot::getCoordinate )
                .sorted ( Comparator.comparingInt ( Coordinate3D::getyCoordinate ) )
                .collect ( Collectors.toList ( ) );

        List< Coordinate3D > sortedZ = nanobots.stream ( )
                .map ( Nanobot::getCoordinate )
                .sorted ( Comparator.comparingInt ( Coordinate3D::getzCoordinate ) )
                .collect ( Collectors.toList ( ) );

        int minX = sortedX.get ( 0 ).getxCoordinate ( );
        int maxX = sortedX.get ( sortedX.size ( ) - 1 ).getxCoordinate ( );
        int minY = sortedY.get ( 0 ).getyCoordinate ( );
        int maxY = sortedY.get ( sortedY.size ( ) - 1 ).getyCoordinate ( );
        int minZ = sortedZ.get ( 0 ).getzCoordinate ( );
        int maxZ = sortedZ.get ( sortedZ.size ( ) - 1 ).getzCoordinate ( );

        int distance = calculateDistance ( nanobots );

        Coordinate3D coordInLargestRange = null;

        while ( distance != 1 )
        {
            distance = distance / 2;

            int previous = 0;

            for ( int x = minX; x < maxX; x += distance )
            {
                for ( int y = minY; y < maxY; y += distance )
                {
                    for ( int z = minZ; z < maxZ; z += distance )
                    {
                        int numberOfBots = 0;

                        for ( Nanobot bot : nanobots )
                        {
                            final Coordinate3D point = new Coordinate3D ( x, y, z );

                            if ( isInRange ( bot, point, distance ) )
                            {
                                numberOfBots++;
                            }

                            if ( previous <= numberOfBots )
                            {
                                coordInLargestRange = point;
                                previous = numberOfBots;
                            }
                        }
                    }
                }
            }

            minX = coordInLargestRange.getxCoordinate ( ) - distance;
            maxX = coordInLargestRange.getxCoordinate ( ) + distance;
            minY = coordInLargestRange.getyCoordinate ( ) - distance;
            maxY = coordInLargestRange.getyCoordinate ( ) + distance;
            minZ = coordInLargestRange.getzCoordinate ( ) - distance;
            maxZ = coordInLargestRange.getzCoordinate ( ) + distance;
        }

        final int shortestDist = Math.abs ( coordInLargestRange.getxCoordinate ( ) )
                                 + Math.abs ( coordInLargestRange.getyCoordinate ( ) )
                                 + Math.abs ( coordInLargestRange.getzCoordinate ( ) );

        return String.valueOf ( shortestDist );
    }

    private static boolean isInRange ( final Nanobot nanobot, final Coordinate3D point, final int distance )
    {
        final int manhattenDistance = nanobot.getCoordinate ( ).manhattenDistance ( point );
        return ( nanobot.getRadius ( ) - manhattenDistance ) / distance >= 0;
    }

    private static int calculateDistance ( final List< Nanobot > nanobots )
    {
        final List< Coordinate3D > sortedX = nanobots.stream ( )
                .map ( Nanobot::getCoordinate )
                .sorted ( Comparator.comparingInt ( Coordinate3D::getxCoordinate ) )
                .collect ( Collectors.toList ( ) );

        final List< Coordinate3D > sortedY = nanobots.stream ( )
                .map ( Nanobot::getCoordinate )
                .sorted ( Comparator.comparingInt ( Coordinate3D::getyCoordinate ) )
                .collect ( Collectors.toList ( ) );

        final List< Coordinate3D > sortedZ = nanobots.stream ( )
                .map ( Nanobot::getCoordinate )
                .sorted ( Comparator.comparingInt ( Coordinate3D::getzCoordinate ) )
                .collect ( Collectors.toList ( ) );


        return Math.max ( Math.abs ( sortedX.get ( sortedX.size ( ) - 1 ).getxCoordinate ( ) - sortedX.get ( 0 ).getxCoordinate ( ) ),
                Math.max ( Math.abs ( sortedY.get ( sortedY.size ( ) - 1 ).getyCoordinate ( ) - sortedY.get ( 0 ).getyCoordinate ( ) ),
                        Math.abs ( sortedZ.get ( sortedZ.size ( ) - 1 ).getzCoordinate ( ) - sortedZ.get ( 0 ).getzCoordinate ( ) ) ) );
    }

    private static List< Nanobot > parseInput ( final String input )
    {
        List< Nanobot > result = Lists.newArrayList ( );

        final String[] lines = splitInput ( input );

        for ( String line : lines )
        {
            final String[] splitLine = line.split ( "," );
            final int xCoord = Integer.parseInt ( splitLine[ 0 ].split ( "<" )[ 1 ] );
            final int yCoord = Integer.parseInt ( splitLine[ 1 ] );
            final int zCoord = Integer.parseInt ( splitLine[ 2 ].split ( ">" )[ 0 ] );

            final int radius = Integer.parseInt ( splitLine[ 3 ].split ( "=" )[ 1 ] );

            result.add ( new Nanobot ( new Coordinate3D ( xCoord, yCoord, zCoord ), radius ) );
        }

        return result;
    }

    private static final class Nanobot
    {
        private final Coordinate3D m_coordinate;

        private final int m_radius;

        public Nanobot (
                final Coordinate3D coordinate,
                final int radius )
        {
            m_coordinate = coordinate;
            m_radius = radius;
        }

        @Override
        public boolean equals ( final Object o )
        {
            if ( this == o ) return true;
            if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
            final Nanobot nanobot = ( Nanobot ) o;
            return m_radius == nanobot.m_radius &&
                   Objects.equals ( m_coordinate, nanobot.m_coordinate );
        }

        @Override
        public int hashCode ( )
        {
            return Objects.hash ( m_coordinate, m_radius );
        }

        public Coordinate3D getCoordinate ( )
        {
            return m_coordinate;
        }

        public int getRadius ( )
        {
            return m_radius;
        }
    }
}
