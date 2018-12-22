package com.laranyman.aoc.eighteen.daytwentytwo;

import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import com.laranyman.aoc.util.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayTwentyTwo implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayTwentyTwo.class );

    @Override
    public String partOne ( final String input )
    {
        final int[] numbers = parseInput ( input );

        final int depth = numbers[ 0 ];
        final Coordinate target = new Coordinate ( numbers[ 1 ], numbers[ 2 ] );

        Map< Coordinate, GridCell > grid = new LinkedHashMap<> ( );

        for ( int i = 0; i < target.getyCoordinate ( ) + 1; i++ )
        {
            for ( int j = 0; j < target.getxCoordinate ( ) + 1; j++ )
            {
                final Coordinate gridCoord = new Coordinate ( j, i );
                grid.put ( gridCoord, new GridCell ( calcGeologicIndex ( gridCoord, target, depth,
                        grid ) ) );
            }
        }

        //printGrid ( grid, depth, target );

        final int riskLevel = grid.entrySet ( )
                .stream ( )
                .mapToInt ( e -> e.getValue ( ).calcType ( depth ).getRiskLevel ( ) )
                .sum ( );

        return String.valueOf ( riskLevel );
    }

    @Override
    public String partTwo ( final String input )
    {
        final int[] numbers = parseInput ( input );

        final int depth = numbers[ 0 ];
        final Coordinate target = new Coordinate ( numbers[ 1 ], numbers[ 2 ] );

        Map< Coordinate, GridCell > grid = new LinkedHashMap<> ( );

        Map< Movement, Integer > distances = Maps.newHashMap ( );

        //arbitrary number added to ensure grid is large enough
        final Coordinate gridBoundaries = new Coordinate ( target.getxCoordinate ( ) + 100,
                target.getyCoordinate ( ) + 100 );

        for ( int i = 0; i < gridBoundaries.getyCoordinate ( ) + 1; i++ )
        {
            for ( int j = 0; j < gridBoundaries.getxCoordinate ( ) + 1; j++ )
            {
                final Coordinate gridCoord = new Coordinate ( j, i );
                grid.put ( gridCoord, new GridCell ( calcGeologicIndex ( gridCoord, target, depth,
                        grid ) ) );

                for ( Tool tools : Tool.values ( ) )
                {
                    distances.put ( new Movement ( gridCoord, tools ), Integer.MAX_VALUE );
                }
            }
        }

        //printGrid ( grid, depth, target );

        //start
        final Movement movement = new Movement ( new Coordinate ( 0, 0 ), Tool.Torch );
        distances.put ( movement, 0 );

        PriorityQueue< QueueEntry > queue = new PriorityQueue<> ( Comparator.comparingInt ( QueueEntry::getDistance ) );
        queue.add ( new QueueEntry ( movement, 0 ) );

        while ( !queue.isEmpty ( ) )
        {
            final QueueEntry queueEntry = queue.remove ( );

            final Coordinate queueEntryCoords = queueEntry.getMovement ( ).getCoordinates ( );

            checkAdjacentCells ( grid, distances, queue, depth, queueEntry, gridBoundaries,
                    queueEntryCoords.getUpCoordinate ( ) );
            checkAdjacentCells ( grid, distances, queue, depth, queueEntry, gridBoundaries,
                    queueEntryCoords.getDownCoordinate ( ) );
            checkAdjacentCells ( grid, distances, queue, depth, queueEntry, gridBoundaries,
                    queueEntryCoords.getLeftCoordinate ( ) );
            checkAdjacentCells ( grid, distances, queue, depth, queueEntry, gridBoundaries,
                    queueEntryCoords.getRightCoordinate ( ) );

            switchTool ( grid, distances, queue, depth, queueEntry, Tool.Torch );
            switchTool ( grid, distances, queue, depth, queueEntry, Tool.ClimbingGear );
            switchTool ( grid, distances, queue, depth, queueEntry, Tool.Neither );
        }

        return String.valueOf ( distances.get ( new Movement ( target, Tool.Torch ) ) );
    }

    private static void switchTool (
            final Map< Coordinate, GridCell > grid,
            final Map< Movement, Integer > distances,
            final PriorityQueue< QueueEntry > queue,
            final int depth,
            final QueueEntry queueEntry,
            final Tool tool )
    {
        if ( queueEntry.getMovement ( ).getTool ( ) == tool )
        {
            return;
        }

        if ( grid.get ( queueEntry.getMovement ( ).getCoordinates ( ) ).acceptTool ( depth, tool ) )
        {
            int d = distances.get ( queueEntry.getMovement ( ) );

            final Movement movement = new Movement ( queueEntry.getMovement ( ).getCoordinates ( ), tool );

            if ( distances.get ( movement ) > 7 + d )
            {
                distances.put ( movement, 7 + d );
                queue.remove ( movement );
                queue.add ( new QueueEntry ( movement, 7 + d ) );
            }
        }
    }

    private static void checkAdjacentCells (
            final Map< Coordinate, GridCell > grid,
            final Map< Movement, Integer > distances,
            final PriorityQueue< QueueEntry > queue,
            final int depth,
            final QueueEntry queueEntry,
            final Coordinate gridBoundaries,
            final Coordinate neighbours )
    {
        if ( neighbours.isInGridBoundaries ( new Coordinate ( 0, 0 ), gridBoundaries ) )
        {
            return;
        }

        if ( grid.get ( neighbours ).acceptTool ( depth, queueEntry.getMovement ( ).getTool ( ) ) )
        {
            int d = distances.get ( queueEntry.getMovement ( ) );

            final Movement movement = new Movement ( neighbours, queueEntry.getMovement ( ).getTool ( ) );

            if ( distances.get ( movement ) > 1 + d )
            {
                distances.put ( movement, 1 + d );
                queue.remove ( movement );
                queue.add ( new QueueEntry ( movement, 1 + d ) );
            }
        }
    }

    private static void printGrid (
            final Map< Coordinate, GridCell > grid,
            final int depth,
            final Coordinate target )
    {
        String line = "";
        int y = 0;

        boolean isTarget;

        for ( Map.Entry< Coordinate, GridCell > entry : grid.entrySet ( ) )
        {
            final int newY = entry.getKey ( ).getyCoordinate ( );

            if ( entry.getKey ( ).equals ( target ) )
            {
                isTarget = true;
            }
            else
            {
                isTarget = false;
            }

            if ( y == newY )
            {
                line += isTarget ? "T" : entry.getValue ( ).calcType ( depth ).m_code;
            }
            else
            {
                LOGGER.info ( line );
                line = isTarget ? "T" : String.valueOf ( entry.getValue ( ).calcType ( depth ).m_code );
                y = newY;
            }
        }

        LOGGER.info ( line );
        LOGGER.info ( "" );
    }

    private static long calcGeologicIndex (
            final Coordinate gridCoord,
            final Coordinate target,
            final int depth,
            final Map< Coordinate, GridCell > grid )
    {
        if ( gridCoord.getyCoordinate ( ) == 0 )
        {
            return gridCoord.getxCoordinate ( ) == 0 ? 0 : gridCoord.getxCoordinate ( ) * 16807;
        }
        if ( gridCoord.getxCoordinate ( ) == 0 )
        {
            return gridCoord.getyCoordinate ( ) * 48271;
        }
        if ( gridCoord.getxCoordinate ( ) == target.getxCoordinate ( )
             && gridCoord.getyCoordinate ( ) == target.getyCoordinate ( ) )
        {
            return 0;
        }
        else
        {
            return grid.get ( gridCoord.getUpCoordinate ( ) ).calcErosionIndex ( depth )
                   * grid.get ( gridCoord.getLeftCoordinate ( ) ).calcErosionIndex ( depth );
        }
    }

    private static int[] parseInput ( final String input )
    {
        final String[] lines = splitInput ( input );
        final int depth = Integer.parseInt ( lines[ 0 ].split ( ":" )[ 1 ].trim ( ) );
        final String[] coordinates = lines[ 1 ].split ( ":" )[ 1 ].split ( "," );
        final int x = Integer.parseInt ( coordinates[ 0 ].trim ( ) );
        final int y = Integer.parseInt ( coordinates[ 1 ].trim ( ) );
        return new int[] { depth, x, y };
    }

    private static final class QueueEntry
    {
        private final Movement m_movement;

        private final int m_distance;

        public QueueEntry ( final Movement movement, final int distance )
        {
            m_movement = movement;
            m_distance = distance;
        }

        public Movement getMovement ( )
        {
            return m_movement;
        }

        public int getDistance ( )
        {
            return m_distance;
        }

        @Override
        public boolean equals ( final Object o )
        {
            if ( this == o ) return true;
            if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
            final QueueEntry that = ( QueueEntry ) o;
            return m_distance == that.m_distance &&
                   Objects.equals ( m_movement, that.m_movement );
        }

        @Override
        public int hashCode ( )
        {
            return Objects.hash ( m_movement, m_distance );
        }
    }

    private static final class Movement
    {
        private final Coordinate m_coordinates;

        private final Tool m_tool;

        public Movement (
                final Coordinate coordinates,
                final Tool tool )
        {
            m_coordinates = coordinates;
            m_tool = tool;
        }

        public Coordinate getCoordinates ( )
        {
            return m_coordinates;
        }

        public Tool getTool ( )
        {
            return m_tool;
        }

        @Override
        public boolean equals ( final Object o )
        {
            if ( this == o ) return true;
            if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
            final Movement movement = ( Movement ) o;
            return Objects.equals ( m_coordinates, movement.m_coordinates ) &&
                   m_tool == movement.m_tool;
        }

        @Override
        public int hashCode ( )
        {
            return Objects.hash ( m_coordinates, m_tool );
        }
    }

    private static final class GridCell
    {
        private final long m_geologicalIndex;

        public GridCell ( final long geologicalIndex )
        {
            m_geologicalIndex = geologicalIndex;
        }

        public long calcErosionIndex ( final int depth )
        {
            return ( m_geologicalIndex + depth ) % 20183;
        }

        public Type calcType ( final int depth )
        {
            switch ( ( int ) ( calcErosionIndex ( depth ) % 3 ) )
            {
                case 0:
                    return Type.Rocky;
                case 1:
                    return Type.Wet;
                case 2:
                    return Type.Narrow;
            }

            throw new AdventOfCodeException ( "Something has gone very wrong...!" );
        }

        public boolean acceptTool ( final int depth, final Tool tool )
        {
            final Type type = calcType ( depth );

            switch ( type )
            {
                case Rocky:
                    return tool == Tool.ClimbingGear || tool == Tool.Torch;

                case Wet:
                    return tool == Tool.ClimbingGear || tool == Tool.Neither;

                case Narrow:
                    return tool == Tool.Neither || tool == Tool.Torch;
            }
            return false;
        }

        @Override
        public boolean equals ( final Object o )
        {
            if ( this == o ) return true;
            if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
            final GridCell gridCell = ( GridCell ) o;
            return m_geologicalIndex == gridCell.m_geologicalIndex;
        }

        @Override
        public int hashCode ( )
        {
            return Objects.hash ( m_geologicalIndex );
        }
    }

    private enum Tool
    {
        Torch,
        ClimbingGear,
        Neither;
    }

    private enum Type
    {
        Rocky ( '.' ),
        Wet ( '=' ),
        Narrow ( '|' );

        private final char m_code;

        Type ( final char code )
        {
            m_code = code;
        }

        public int getRiskLevel ( )
        {
            if ( this == Rocky )
            {
                return 0;
            }
            if ( this == Wet )
            {
                return 1;
            }
            if ( this == Narrow )
            {
                return 2;
            }

            throw new AdventOfCodeException ( "Something has gone very wrong...!" );
        }
    }
}
