package com.laranyman.aoc.eighteen.daythirteen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.eighteen.dayeight.Node;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import com.laranyman.aoc.util.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;
import static com.sun.tools.doclint.Entity.ne;

/**
 * @author Lara
 */
public class DayThirteen implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayThirteen.class );

    @Override
    public String partOne ( final String input )
    {
        final char[][] grid = parseInput ( input );

        List< Node > nodes = getNodes ( grid );

        final Map< Corners, Map< Direction, Direction > > cornerMap = Corners.getCornersToDirection ( );

        while ( true )
        {
            final MoveResult moveResult = move ( grid, new ArrayList<> ( nodes ), cornerMap );

            final List< Coordinate > crashes = moveResult.m_crashes;

            if ( !crashes.isEmpty ( ) )
            {
                final Coordinate crashSite = crashes.get ( 0 );
                return crashSite.getxCoordinate ( ) + "," + crashSite.getyCoordinate ( );
            }

            nodes = moveResult.m_nodes;
        }
    }

    @Override
    public String partTwo ( final String input )
    {
        final char[][] grid = parseInput ( input );

        List< Node > nodes = getNodes ( grid );

        final Map< Corners, Map< Direction, Direction > > cornerMap = Corners.getCornersToDirection ( );

        while ( true )
        {
            final MoveResult moveResult = move ( grid, new ArrayList<> ( nodes ), cornerMap );

            if ( moveResult.m_nodes.size ( ) == 1 )
            {
                final Coordinate remainingNode = moveResult.m_nodes.get ( 0 ).m_coordinate;
                return remainingNode.getxCoordinate ( ) + "," + remainingNode.getyCoordinate ( );
            }

            LOGGER.info ( "Remaining nodes : {}", moveResult.m_nodes.size ( ) );

            nodes = moveResult.m_nodes;
        }
    }

    private static List< Node > getNodes ( final char[][] grid )
    {
        List< Node > nodes = Lists.newArrayList ( );

        final List< Character > characters = Direction.getCharacters ( );

        for ( int i = 0; i < grid.length; i++ )
        {
            for ( int j = 0; j < grid[ 0 ].length; j++ )
            {
                final char character = grid[ i ][ j ];

                if ( characters.contains ( character ) )
                {
                    nodes.add ( new Node ( new Coordinate ( j, i ),
                            Direction.parseCharacter ( character ) ) );
                }
            }
        }

        return nodes;
    }

    private static char[][] parseInput ( final String input )
    {
        final String[] inputSplit = splitInput ( input );

        char[][] result = new char[ inputSplit.length ][ inputSplit[ 0 ].length ( ) ];

        for ( int i = 0; i < inputSplit.length; i++ )
        {
            for ( int j = 0; j < inputSplit[ 0 ].length ( ); j++ )
            {
                result[ i ][ j ] = inputSplit[ i ].charAt ( j );
            }
        }

        return result;
    }

    private static MoveResult move (
            final char[][] grid,
            final List< Node > remainingNodes,
            final Map< Corners, Map< Direction, Direction > > cornerMap )
    {
        List< Coordinate > crashes = Lists.newArrayList ( );

        List< Node > nodesToRemove = Lists.newArrayList ( );

        for ( int i = 0; i < remainingNodes.size ( ); i++ )
        {
            final Node node = remainingNodes.get ( i );

            if ( nodesToRemove.contains ( node ) )
            {
                continue;
            }

            final List< Coordinate > coordsOfNodes = getCoordsFromNodes ( remainingNodes );

            final Coordinate newCoord = addDirectionToCoordinate ( node.m_coordinate, node.m_direction );

            if ( coordsOfNodes.contains ( newCoord ) )
            {
                crashes.add ( newCoord );

                for ( final Node remain : remainingNodes )
                {
                    if ( remain.equals ( node ) || remain.m_coordinate.equals ( newCoord ) )
                    {
                        nodesToRemove.add ( remain );
                    }
                }
            }
            else
            {
                final char currentSquareOfNewNode = grid[ newCoord.getyCoordinate ( ) ][ newCoord.getxCoordinate ( ) ];

                final Direction newDirection;
                Intersection newIntersection = node.m_intersection;

                if ( Corners.getCharacters ( ).contains ( currentSquareOfNewNode ) )
                {
                    newDirection = cornerMap.get ( Corners.parseCharacter ( currentSquareOfNewNode ) )
                            .get ( node.m_direction );
                }
                else if ( currentSquareOfNewNode == '+' )
                {
                    newDirection = getNextDirection ( node.m_intersection, node.m_direction );
                    newIntersection = node.m_intersection.getNextDirection ( );
                }
                else
                {
                    newDirection = node.m_direction;
                }

                final Node newNode = new Node ( newCoord, newDirection, newIntersection );
                remainingNodes.set ( i, newNode );
            }
        }

        remainingNodes.removeAll ( nodesToRemove );

        return new MoveResult ( remainingNodes, crashes );
    }

    private static List< Coordinate > getCoordsFromNodes ( final List< Node > nodes )
    {
        return nodes.stream ( )
                .map ( e -> e.m_coordinate )
                .collect ( Collectors.toList ( ) );
    }

    private static Direction getNextDirection ( final Intersection intersection, final Direction currentDirection )
    {
        if ( intersection == Intersection.Left )
        {
            switch ( currentDirection )
            {
                case Up:
                    return Direction.Left;
                case Right:
                    return Direction.Up;
                case Down:
                    return Direction.Right;
                case Left:
                    return Direction.Down;
            }
        }
        else if ( intersection == Intersection.Right )
        {
            switch ( currentDirection )
            {
                case Up:
                    return Direction.Right;
                case Right:
                    return Direction.Down;
                case Down:
                    return Direction.Left;
                case Left:
                    return Direction.Up;
            }
        }

        return currentDirection;
    }


    private static Coordinate addDirectionToCoordinate (
            final Coordinate coordinate,
            final Direction direction )
    {
        switch ( direction )
        {
            case Up:
                return new Coordinate ( coordinate.getxCoordinate ( ), coordinate.getyCoordinate ( ) - 1 );
            case Right:
                return new Coordinate ( coordinate.getxCoordinate ( ) + 1, coordinate.getyCoordinate ( ) );
            case Down:
                return new Coordinate ( coordinate.getxCoordinate ( ), coordinate.getyCoordinate ( ) + 1 );
            case Left:
                return new Coordinate ( coordinate.getxCoordinate ( ) - 1, coordinate.getyCoordinate ( ) );
            default:
                throw new AdventOfCodeException ( String.format ( "Unknown direction : [%s]", direction ) );
        }
    }

    public enum Direction
    {
        Up ( '^' ),
        Right ( '>' ),
        Down ( 'v' ),
        Left ( '<' );

        private final char m_character;

        Direction ( final char character )
        {
            m_character = character;
        }

        public static List< Character > getCharacters ( )
        {
            List< Character > characters = Lists.newArrayList ( );
            characters.add ( Up.m_character );
            characters.add ( Right.m_character );
            characters.add ( Down.m_character );
            characters.add ( Left.m_character );
            return characters;
        }

        public static Direction parseCharacter ( final char character )
        {
            if ( character == Left.m_character )
            {
                return Left;
            }
            if ( character == Right.m_character )
            {
                return Right;
            }
            if ( character == Down.m_character )
            {
                return Down;
            }
            if ( character == Up.m_character )
            {
                return Up;
            }

            throw new AdventOfCodeException ( String.format ( "Unknown input : %s", character ) );
        }
    }

    public enum Corners
    {
        SlantRight ( '/' ),
        SlantLeft ( '\\' );

        private final char m_character;

        Corners ( final char corner )
        {
            m_character = corner;
        }

        public static List< Character > getCharacters ( )
        {
            List< Character > characters = Lists.newArrayList ( );
            characters.add ( SlantLeft.m_character );
            characters.add ( SlantRight.m_character );
            return characters;
        }

        public static Map< Corners, Map< Direction, Direction > > getCornersToDirection ( )
        {
            Map< Corners, Map< Direction, Direction > > result = Maps.newHashMap ( );

            Map< Direction, Direction > directionsSlantLeft = Maps.newHashMap ( );
            directionsSlantLeft.put ( Direction.Up, Direction.Left );
            directionsSlantLeft.put ( Direction.Right, Direction.Down );
            directionsSlantLeft.put ( Direction.Down, Direction.Right );
            directionsSlantLeft.put ( Direction.Left, Direction.Up );

            result.put ( SlantLeft, directionsSlantLeft );

            Map< Direction, Direction > directionsSlantRight = Maps.newHashMap ( );
            directionsSlantRight.put ( Direction.Up, Direction.Right );
            directionsSlantRight.put ( Direction.Right, Direction.Up );
            directionsSlantRight.put ( Direction.Down, Direction.Left );
            directionsSlantRight.put ( Direction.Left, Direction.Down );

            result.put ( SlantRight, directionsSlantRight );

            return result;

        }

        public static Corners parseCharacter ( final char character )
        {
            if ( character == SlantRight.m_character )
            {
                return SlantRight;
            }
            if ( character == SlantLeft.m_character )
            {
                return SlantLeft;
            }

            throw new AdventOfCodeException ( String.format ( "Unknown input : %s", character ) );
        }
    }

    enum Intersection
    {
        Left,
        Straight,
        Right;

        public Intersection getNextDirection ( )
        {
            switch ( this )
            {
                case Left:
                    return Straight;
                case Straight:
                    return Right;
                case Right:
                    return Left;
                default:
                    throw new AdventOfCodeException ( "Unknown case" );
            }
        }
    }

    private static class MoveResult
    {
        private final List< Node > m_nodes;

        private final List< Coordinate > m_crashes;

        public MoveResult ( final List< Node > nodes, final List< Coordinate > crashes )
        {
            m_nodes = nodes;
            m_crashes = crashes;
        }
    }

    public static final class Node
    {
        private final Coordinate m_coordinate;

        private final Direction m_direction;

        private final Intersection m_intersection;

        public Node (
                final Coordinate coordinate,
                final Direction direction )
        {
            m_coordinate = coordinate;
            m_direction = direction;
            m_intersection = Intersection.Left;
        }

        public Node (
                final Coordinate coordinate,
                final Direction direction,
                final Intersection intersection )
        {
            m_coordinate = coordinate;
            m_direction = direction;
            m_intersection = intersection;
        }

        @Override
        public boolean equals ( final Object o )
        {
            if ( this == o ) return true;
            if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
            final Node node = ( Node ) o;
            return Objects.equals ( m_coordinate, node.m_coordinate ) &&
                   m_direction == node.m_direction &&
                   m_intersection == node.m_intersection;
        }

        @Override
        public int hashCode ( )
        {
            return Objects.hash ( m_coordinate, m_direction, m_intersection );
        }
    }
}
