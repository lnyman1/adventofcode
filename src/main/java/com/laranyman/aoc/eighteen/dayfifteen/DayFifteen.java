package com.laranyman.aoc.eighteen.dayfifteen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import com.laranyman.aoc.util.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayFifteen implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayFifteen.class );

    private static CoordinatorSort s_coordinatorSort = new CoordinatorSort ( );

    @Override
    public String partOne ( final String input )
    {
        final Map< Coordinate, GridCell > grid = parseInput ( input );

        int round = 0;

        while ( true )
        {
            final Map< Coordinate, GridCell > remainingBandits = getRemainingBandits ( grid );

            for ( Map.Entry< Coordinate, GridCell > entry : remainingBandits.entrySet ( ) )
            {
                final GridCell value = entry.getValue ( );
                final Coordinate coordinate = entry.getKey ( );

                if ( !grid.get ( coordinate ).isFightingBandit ( ) )
                {
                    continue;
                }

                final List< Coordinate > enemyCoordinates = getEnemyCooridnates ( value.getEnemy ( ), grid );

                if ( enemyCoordinates.isEmpty ( ) )
                {
                    final Map< Coordinate, GridCell > bandits = getRemainingBandits ( grid );

                    final int pointsTotal = bandits.entrySet ( )
                            .stream ( )
                            .map ( e -> grid.get ( e.getKey ( ) ) )
                            .mapToInt ( e -> e.m_points )
                            .sum ( );

                    return String.valueOf ( round * pointsTotal );
                }

                final List< Coordinate > neighbours = coordinate.getNeighbours ( );

                final List< Coordinate > neighbouringEnemies = getNeighbouringEnemies (
                        neighbours,
                        enemyCoordinates );

                Coordinate newPosition = coordinate;

                if ( neighbouringEnemies.isEmpty ( ) )
                {
                    final Coordinate movedPosition = move ( coordinate, enemyCoordinates, grid, value );

                    if ( movedPosition != null )
                    {
                        newPosition = movedPosition;
                    }
                }

                final List< Coordinate > newNeighbouringEnemies = getNeighbouringEnemies (
                        newPosition.getNeighbours ( ),
                        enemyCoordinates );


                if ( !newNeighbouringEnemies.isEmpty ( ) )
                {
                    fightOne ( newNeighbouringEnemies, grid );
                }

            }

            round++;
            LOGGER.info ( "Round completed : {}", round );
            printGrid ( grid );
        }

    }

    private void printGrid ( final Map< Coordinate, GridCell > grid )
    {
        String line = "";
        int y = 0;

        for ( Map.Entry< Coordinate, GridCell > entry : grid.entrySet ( ) )
        {
            final int newY = entry.getKey ( ).getyCoordinate ( );

            if ( y == newY )
            {
                line += entry.getValue ( ).m_unit.m_code;
            }
            else
            {
                LOGGER.info ( line );
                line = String.valueOf ( entry.getValue ( ).m_unit.m_code );
                y = newY;
            }
        }
    }

    @Override
    public String partTwo ( final String input, long... elfAttack )
    {
        final Map< Coordinate, GridCell > grid = parseInput ( input );

        int round = 0;

        while ( true )
        {

            for ( Map.Entry< Coordinate, GridCell > entry : getRemainingBandits ( grid ).entrySet ( ) )
            {
                final GridCell value = entry.getValue ( );
                final Coordinate coordinate = entry.getKey ( );

                if ( !getRemainingBandits ( grid ).containsKey ( coordinate ) )
                {
                    continue;
                }

                final Map< Coordinate, GridCell > banditsLogging = getRemainingBandits ( grid );

                final String pointsLeftNew = banditsLogging.entrySet ( )
                        .stream ( )
                        .map ( e -> grid.get ( e.getKey ( ) ) )
                        .map ( e -> String.valueOf ( e.m_points ) )
                        .collect ( Collectors.joining ( ", " ) );

//                LOGGER.info ( "Remaining Bandits : " + banditsLogging.size ( ) + " Points : " + pointsLeftNew );


                final List< Coordinate > enemyCoordinates = getEnemyCooridnates ( value.getEnemy ( ), grid );

                if ( enemyCoordinates.isEmpty ( ) )
                {
                    final Map< Coordinate, GridCell > bandits = getRemainingBandits ( grid );

                    final int pointsTotal = bandits.entrySet ( )
                            .stream ( )
                            .map ( e -> grid.get ( e.getKey ( ) ) )
                            .mapToInt ( e -> e.m_points )
                            .sum ( );

                    return String.valueOf ( round * pointsTotal );
                }

                final List< Coordinate > neighbours = coordinate.getNeighbours ( );

                final List< Coordinate > neighbouringEnemies = getNeighbouringEnemies (
                        neighbours,
                        enemyCoordinates );

                Coordinate newPosition = coordinate;

                if ( neighbouringEnemies.isEmpty ( ) )
                {
                    final Coordinate movedPosition = move ( coordinate, enemyCoordinates, grid, value );

                    if ( movedPosition != null )
                    {
                        newPosition = movedPosition;
                    }
                }

                final List< Coordinate > newNeighbouringEnemies = getNeighbouringEnemies (
                        newPosition.getNeighbours ( ),
                        enemyCoordinates );


                if ( !newNeighbouringEnemies.isEmpty ( ) )
                {
                    fightTwo ( newNeighbouringEnemies, grid,
                            Integer.parseInt ( String.valueOf ( elfAttack[ 0 ] ) ) );
                }
            }

            round++;

            LOGGER.info ( "Round completed : {}", round );
        }
    }

    private static Map< Coordinate, GridCell > getRemainingBandits ( final Map< Coordinate, GridCell > grid )
    {
        return grid.entrySet ( )
                .stream ( )
                .filter ( e -> e.getValue ( ).isFightingBandit ( ) )
                .sorted ( ( e1, e2 ) -> s_coordinatorSort.compare ( e1.getKey ( ), e2.getKey ( ) ) )
                .collect ( Collectors.toMap ( Map.Entry::getKey, Map.Entry::getValue, ( e1, e2 ) -> e1,
                        LinkedHashMap::new ) );
    }

    private static List< Coordinate > getRange (
            final Coordinate from,
            final Coordinate to,
            final Map< Coordinate, GridCell > grid )
    {
        Set< Coordinate > visited = Sets.newHashSet ( );
        List< Coordinate > unVisited = Lists.newArrayList ( );

        Map< Coordinate, Distance > distances = Maps.newHashMap ( );
        distances.put ( from, new Distance ( 0 ) );

        Coordinate start = from;

        while ( true )
        {
            final List< Coordinate > unVisitedCoordinates = start.getNeighbours ( )
                    .stream ( )
                    .filter ( e -> grid.get ( e ).m_unit == Unit.Space )
                    .filter ( e -> !unVisited.contains ( e ) )
                    .filter ( e -> !visited.contains ( e ) )
                    .collect ( Collectors.toList ( ) );

            unVisited.addAll ( unVisitedCoordinates );

            for ( Coordinate unVisitedCoord : unVisited )
            {
                final Distance distanceOfStart = distances.get ( start );

                if ( distanceOfStart == null )
                {
                    throw new AdventOfCodeException ( "Something has gone wrong...." );
                }

                final int distance = distanceOfStart.m_distance + 1;

                final Distance unvisitedDistance = distances.get ( unVisitedCoord );

                final int newDistance = unvisitedDistance != null
                        ? unvisitedDistance.m_distance
                        : Integer.MAX_VALUE;

                if ( distance < newDistance )
                {
                    distances.put ( unVisitedCoord, new Distance ( distance, start ) );
                }
            }

            unVisited.remove ( start );
            visited.add ( start );

            if ( start.equals ( to ) )
            {
                Distance distanceOfTo = distances.get ( to );

                if ( distanceOfTo == null )
                {
                    throw new AdventOfCodeException ( "Something has gone wrong again....!" );
                }

                List< Coordinate > path = Lists.newArrayList ( );
                path.add ( to );

                while ( distanceOfTo.m_previousPoint != null )
                {
                    path.add ( distanceOfTo.m_previousPoint );
                    distanceOfTo = distances.get ( distanceOfTo.m_previousPoint );
                }

                Collections.reverse ( path );
                return path.subList ( 1, path.size ( ) );
            }

            final Optional< Coordinate > unVisitedRemaining = unVisited.stream ( )
                    .sorted ( Comparator.comparingInt ( e -> distances.get ( e ).m_distance ) )
                    .findFirst ( );

            if ( !unVisitedRemaining.isPresent ( ) )
            {
                return null;
            }

            start = unVisitedRemaining.get ( );

        }
    }

    private static void fightOne (
            final List< Coordinate > neighbouringEnemies,
            final Map< Coordinate, GridCell > grid )
    {
        neighbouringEnemies.sort ( Comparator.comparingInt ( e -> grid.get ( e ).m_points ) );
        final Coordinate healthiestNeighbour = neighbouringEnemies.get ( 0 );
        GridCell gridCell = grid.get ( healthiestNeighbour );
        final int health = gridCell.m_points - 3;

        if ( health > 0 )
        {
            grid.put ( healthiestNeighbour, new GridCell ( gridCell.m_unit, health ) );
            LOGGER.info ( "Coordinate : {}, Unit : {}, Health : {}",
                    healthiestNeighbour.getxCoordinate ( ) + "," + healthiestNeighbour.getyCoordinate ( ),
                    gridCell.m_unit, health );
        }
        else
        {
            //die!
            grid.put ( healthiestNeighbour, new GridCell ( Unit.Space, 0 ) );
            LOGGER.info ( "DIED!! Coordinate : {}, Unit : {}, Health : {}", healthiestNeighbour.getxCoordinate ( ) +
                                                                            "," + healthiestNeighbour
                                                                                    .getyCoordinate ( ),
                    gridCell.m_unit, 0 );
        }
    }

    private static void fightTwo (
            final List< Coordinate > neighbouringEnemies,
            final Map< Coordinate, GridCell > grid,
            final int elfAttack )
    {
        neighbouringEnemies.sort ( Comparator.comparingInt ( e -> grid.get ( e ).m_points ) );
        final Coordinate healthiestNeighbour = neighbouringEnemies.get ( 0 );
        GridCell gridCell = grid.get ( healthiestNeighbour );
        final int health = gridCell.m_points - ( gridCell.m_unit == Unit.Goblin ? elfAttack : 3 );

        if ( health > 0 )
        {
            grid.put ( healthiestNeighbour, new GridCell ( gridCell.m_unit, health ) );
            LOGGER.info ( "Coordinate : {}, Unit : {}, Health : {}",
                    healthiestNeighbour.getxCoordinate ( ) + "," + healthiestNeighbour.getyCoordinate ( ),
                    gridCell.m_unit, health );
        }
        else
        {
            //die!
            grid.put ( healthiestNeighbour, new GridCell ( Unit.Space, 0 ) );
            LOGGER.info ( "DIED!! Coordinate : {}, Unit : {}, Health : {}", healthiestNeighbour.getxCoordinate ( ) +
                                                                            "," + healthiestNeighbour
                                                                                    .getyCoordinate ( ),
                    gridCell.m_unit, 0 );

            if ( gridCell.m_unit == Unit.Elf )
            {
                throw new AdventOfCodeException ( String.format ( "Need higher elf attack than %s", elfAttack ) );
            }
        }
    }

    private static Coordinate move (
            final Coordinate currentPosition,
            final List< Coordinate > enemyCoordinates,
            final Map< Coordinate, GridCell > grid,
            final GridCell currentUnit )
    {
        final List< Coordinate > space = enemyCoordinates.stream ( )
                .map ( Coordinate::getNeighbours )
                .flatMap ( Collection::stream )
                .filter ( e -> grid.get ( e ).m_unit == Unit.Space )
                .collect ( Collectors.toList ( ) );


        List< Distance > distances = Lists.newArrayList ( );

        for ( Coordinate target : space )
        {
            final List< Coordinate > range = getRange ( currentPosition, target, grid );

            if ( range != null && range.size ( ) < Integer.MAX_VALUE )
            {
                distances.add ( new Distance ( range.size ( ), target ) );
            }
        }

        final List< Distance > ranges = distances
                .stream ( )
                .sorted ( Comparator.comparingInt ( e -> e.m_distance ) )
                .collect ( Collectors.toList ( ) );

        if ( !ranges.isEmpty ( ) )
        {
            final Coordinate selectedTarget = ranges
                    .stream ( )
                    .min ( Comparator.comparingInt ( e -> e.m_distance ) )
                    .map ( e -> e.m_previousPoint ).get ( );

            final List< Coordinate > moves = getRange ( currentPosition, selectedTarget, grid );
            final Coordinate movedPosition = moves.get ( 0 );
            grid.put ( currentPosition, new GridCell ( Unit.Space, 0 ) );
            grid.put ( movedPosition, currentUnit );

            return movedPosition;
        }

        return null;
    }

    private static List< Coordinate > getNeighbouringEnemies (
            final List< Coordinate > neighbours,
            final List< Coordinate > enemies )
    {
        return neighbours.stream ( )
                .filter ( enemies::contains )
                .collect ( Collectors.toList ( ) );
    }

    private static List< Coordinate > getEnemyCooridnates (
            final Unit enemy,
            final Map< Coordinate, GridCell > grid )
    {
        return grid.entrySet ( )
                .stream ( )
                .filter ( e -> e.getValue ( ).isFightingBandit ( ) )
                .filter ( e -> e.getValue ( ).m_unit == enemy )
                .map ( Map.Entry::getKey )
                .collect ( Collectors.toList ( ) );
    }

    private static Map< Coordinate, GridCell > parseInput ( final String input )
    {
        final String[] lines = splitInput ( input );

        Map< Coordinate, GridCell > grid = Maps.newHashMap ( );

        for ( int i = 0; i < lines.length; i++ )
        {
            for ( int j = 0; j < lines[ i ].length ( ); j++ )
            {
                final Unit unit = Unit.parseUnit ( lines[ i ].charAt ( j ) );
                final GridCell gridCell = new GridCell ( unit, unit == Unit.Elf || unit == Unit.Goblin ? 200 : 0 );
                grid.put ( new Coordinate ( j, i ), gridCell );

            }
        }

        return grid.entrySet ( )
                .stream ( )
                .sorted ( ( e1, e2 ) -> s_coordinatorSort.compare ( e1.getKey ( ), e2.getKey ( ) ) )
                .collect ( Collectors.toMap ( Map.Entry::getKey, Map.Entry::getValue, ( e1, e2 ) -> e1,
                        LinkedHashMap::new ) );
    }

    private static final class CoordinatorSort implements Comparator< Coordinate >
    {
        @Override
        public int compare ( final Coordinate x1, final Coordinate x2 )
        {
            int result = Double.compare ( x1.getyCoordinate ( ), x2.getyCoordinate ( ) );
            if ( result == 0 )
            {
                result = Double.compare ( x1.getxCoordinate ( ), x2.getxCoordinate ( ) );
            }
            return result;
        }
    }

    private static final class Distance
    {
        private final int m_distance;

        private final Coordinate m_previousPoint;

        public Distance ( final int distance )
        {
            m_distance = distance;
            m_previousPoint = null;
        }

        public Distance ( final int distance, final Coordinate previousPoint )
        {
            m_distance = distance;
            m_previousPoint = previousPoint;
        }
    }


    private static final class GridCell
    {
        private final Unit m_unit;

        private final int m_points;

        public GridCell ( final Unit unit, final int points )
        {
            m_unit = unit;
            m_points = points;
        }

        public boolean isFightingBandit ( )
        {
            return m_unit == Unit.Goblin || m_unit == Unit.Elf;
        }

        public Unit getEnemy ( )
        {
            return m_unit == Unit.Elf
                    ? Unit.Goblin
                    : m_unit == Unit.Goblin ?
                    Unit.Elf
                    : m_unit;
        }
    }

    private enum Unit
    {
        Wall ( '#' ),
        Space ( '.' ),
        Elf ( 'E' ),
        Goblin ( 'G' );

        private final char m_code;

        Unit ( final char code )
        {
            m_code = code;
        }

        public static Unit parseUnit ( final char code )
        {
            if ( code == Wall.m_code )
            {
                return Wall;
            }
            else if ( code == Space.m_code )
            {
                return Space;
            }
            else if ( code == Elf.m_code )
            {
                return Elf;
            }
            else if ( code == Goblin.m_code )
            {
                return Goblin;
            }

            throw new AdventOfCodeException ( String.format ( "Unknown input : %s", code ) );
        }
    }
}
