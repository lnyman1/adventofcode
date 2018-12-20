package com.laranyman.aoc.eighteen.daytwenty;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import com.laranyman.aoc.util.Coordinate;
import com.laranyman.aoc.util.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Queue;

/**
 * @author Lara
 */
public class DayTwenty implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        final Queue< Character > regex = parseInput ( input );

        final Map< Coordinate, Integer > distances = process ( regex );

        final int maxDist = distances.values ( )
                .stream ( )
                .mapToInt ( e -> e )
                .max ( )
                .getAsInt ( );

        return String.valueOf ( maxDist );
    }


    @Override
    public String partTwo ( final String input )
    {
        final Queue< Character > regex = parseInput ( input );

        final Map< Coordinate, Integer > distances = process ( regex );

        final long number = distances.values ( )
                .stream ( )
                .mapToInt ( e -> e )
                .filter ( e -> e >= 1000 )
                .count ( );

        return String.valueOf ( number );
    }

    private static Map< Coordinate, Integer > process ( final Queue< Character > regex )
    {
        Coordinate position = new Coordinate ( 0, 0 );

        Map< Coordinate, List< Coordinate > > visited = Maps.newHashMap ( );

        Coordinate prevCoordinate = position;

        Map< Coordinate, Integer > distances = Maps.newHashMap ( );

        Queue< Coordinate > positions = new LinkedList<> ( );

        while ( !regex.isEmpty ( ) )
        {
            final Character character = regex.remove ( );

            if ( character == '$' || character == '^' )
            {
                continue;
            }

            if ( character == '(' )
            {
                positions.offer ( new Coordinate ( position.getxCoordinate ( ), position.getyCoordinate ( ) ) );
            }
            else if ( character == ')' )
            {
                position = ( ( LinkedList< Coordinate > ) positions ).remove ( positions.size ( ) - 1 );
            }
            else if ( character == '|' )
            {
                position = ( ( LinkedList< Coordinate > ) positions ).peekLast ( );
            }
            else
            {
                final Coordinate pCoord = prevCoordinate;
                position = resolveDirection ( Direction.parseCharacter ( character ), position );
                visited.computeIfPresent ( position, ( k, v ) -> {
                    v.add ( pCoord );
                    return v;
                } );
                visited.computeIfAbsent ( position, ( key ) -> {
                    List< Coordinate > value = Lists.newArrayList ( );
                    value.add ( pCoord );
                    return value;
                } );

                if ( distances.get ( position ) != null && distances.get ( position ) != 0 )
                {
                    distances.put ( position, Math.min ( distances.get ( position ),
                            distances.get ( prevCoordinate ) + 1 ) );
                }
                else
                {
                    final Integer prevDist = distances.get ( prevCoordinate );
                    distances.put ( position, prevDist == null ? 1 : prevDist + 1 );
                }
            }

            prevCoordinate = position;
        }

        return distances;
    }

    private static Coordinate resolveDirection (
            final Direction direction,
            final Coordinate currentLocation )
    {
        if ( direction == Direction.North )
        {
            return currentLocation.getUpCoordinate ( );
        }
        if ( direction == Direction.East )
        {
            return currentLocation.getRightCoordinate ( );
        }
        if ( direction == Direction.South )
        {
            return currentLocation.getDownCoordinate ( );
        }
        if ( direction == Direction.West )
        {
            return currentLocation.getLeftCoordinate ( );
        }

        throw new AdventOfCodeException ( String.format ( "Unhandled direction %s", direction.name ( ) ) );

    }

    private static Queue< Character > parseInput ( final String input )
    {
        Queue< Character > queue = new LinkedList<> ( );
        for ( char c : input.toCharArray ( ) )
        {
            queue.offer ( c );
        }
        return queue;
    }

    enum Area
    {
        Room ( '.' ),
        Walls ( '#' ),
        HDoor ( '-' ),
        VDoor ( '|' ),
        CurrentLocation ( 'X' );

        private final char m_code;

        Area ( final char code )
        {
            m_code = code;
        }

        public boolean isDoor ( )
        {
            return this == HDoor || this == VDoor;
        }

        public static Area parseCharacter ( final char character )
        {
            if ( character == Room.m_code )
            {
                return Room;
            }
            if ( character == Walls.m_code )
            {
                return Walls;
            }
            if ( character == HDoor.m_code )
            {
                return HDoor;
            }
            if ( character == VDoor.m_code )
            {
                return VDoor;
            }
            if ( character == CurrentLocation.m_code )
            {
                return CurrentLocation;
            }

            throw new AdventOfCodeException ( String.format ( "Unknown character", character ) );
        }
    }
}
