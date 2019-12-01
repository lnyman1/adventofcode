package com.laranyman.aoc.eighteen.dayeighteen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import com.laranyman.aoc.util.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayEighteen implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayEighteen.class );

    @Override
    public String partOne ( final String input )
    {
        Map< Coordinate, LumbarCollection > grid = parseInput ( input );

        //printGrid ( grid );

        int count = 0;

        for ( int i = 0; i < 10; i++ )
        {
            grid = round ( grid );

            LOGGER.info ( "Round : {}", count++ );
            //printGrid ( grid );
        }


        final long noOfTrees = grid.entrySet ( )
                .stream ( )
                .filter ( e -> e.getValue ( ) == LumbarCollection.Trees )
                .count ( );

        final long noOfLumbaryards = grid.entrySet ( )
                .stream ( )
                .filter ( e -> e.getValue ( ) == LumbarCollection.Lumbaryard )
                .count ( );

        return String.valueOf ( noOfTrees * noOfLumbaryards );
    }

    @Override
    public String partTwo ( final String input )
    {
        Map< Coordinate, LumbarCollection > startState = parseInput ( input );

        List< Map< Coordinate, LumbarCollection > > collection = Lists.newArrayList ( );

        //printGrid ( grid );

        int count = 0;

        for ( int i = 1; i < 1000000000; i++ )
        {
            final Map< Coordinate, LumbarCollection > newGrid = round ( startState );

            LOGGER.info ( "Round : {}", count++ );

            if ( collection.contains ( newGrid ) )
            {
                final int idx = collection.indexOf ( newGrid );
                LOGGER.info ( "Index" + idx );
                final int cycleSize = collection.size ( ) - idx;
                LOGGER.info ( "Cycle size" + cycleSize );
                final Map< Coordinate, LumbarCollection > finalState =
                        collection.get ( ( ( 1000000000 - i ) % cycleSize ) + idx );

                final long noOfTrees = finalState.entrySet ( )
                        .stream ( )
                        .filter ( e -> e.getValue ( ) == LumbarCollection.Trees )
                        .count ( );

                final long noOfLumbaryards = finalState.entrySet ( )
                        .stream ( )
                        .filter ( e -> e.getValue ( ) == LumbarCollection.Lumbaryard )
                        .count ( );

                return String.valueOf ( noOfTrees * noOfLumbaryards );

            }

            collection.add ( newGrid );
            startState = newGrid;
            //printGrid ( grid );
        }

        throw new AdventOfCodeException ( "No pattern found.  Too expensive to run the whole thing!" );
    }

    private static void printGrid ( final Map< Coordinate, LumbarCollection > grid )
    {
        String line = "";
        int y = 0;

        for ( Map.Entry< Coordinate, LumbarCollection > entry : grid.entrySet ( ) )
        {
            final int newY = entry.getKey ( ).getyCoordinate ( );

            if ( y == newY )
            {
                line += entry.getValue ( ).m_code;
            }
            else
            {
                LOGGER.info ( line );
                line = String.valueOf ( entry.getValue ( ).m_code );
                y = newY;
            }
        }

        LOGGER.info ( line );
        LOGGER.info ( "" );
    }

    private static Map< Coordinate, LumbarCollection > round ( final Map< Coordinate, LumbarCollection > grid )
    {
        final Map< Coordinate, LumbarCollection > newGrid = new LinkedHashMap<> ( );

        for ( Map.Entry< Coordinate, LumbarCollection > entry : grid.entrySet ( ) )
        {
            final LumbarCollection currentSquareCol = entry.getValue ( );

            if ( currentSquareCol == LumbarCollection.OpenGround )
            {
                final List< Coordinate > neighbours = entry.getKey ( ).getEightNeighbours ( );

                int treeCount = 0;

                for ( Coordinate coordinate : neighbours )
                {
                    final LumbarCollection lumbarCol = grid.get ( coordinate );

                    if ( lumbarCol != null && lumbarCol == LumbarCollection.Trees )
                    {
                        treeCount++;
                    }
                }

                if ( treeCount >= 3 )
                {
                    newGrid.put ( entry.getKey ( ), currentSquareCol.next ( ) );
                }
                else
                {
                    newGrid.put ( entry.getKey ( ), currentSquareCol );
                }
            }
            else if ( currentSquareCol == LumbarCollection.Trees )
            {
                final List< Coordinate > neighbours = entry.getKey ( ).getEightNeighbours ( );

                int treeCount = 0;

                for ( Coordinate coordinate : neighbours )
                {
                    final LumbarCollection lumbarCol = grid.get ( coordinate );

                    if ( lumbarCol != null && lumbarCol == LumbarCollection.Lumbaryard )
                    {
                        treeCount++;
                    }
                }

                if ( treeCount >= 3 )
                {
                    newGrid.put ( entry.getKey ( ), currentSquareCol.next ( ) );
                }
                else
                {
                    newGrid.put ( entry.getKey ( ), currentSquareCol );
                }
            }
            else if ( currentSquareCol == LumbarCollection.Lumbaryard )
            {
                final List< Coordinate > neighbours = entry.getKey ( ).getEightNeighbours ( );

                boolean lumbaryard = false;
                boolean tree = false;

                for ( Coordinate coordinate : neighbours )
                {
                    final LumbarCollection lumbarCol = grid.get ( coordinate );

                    if ( lumbarCol != null && lumbarCol == LumbarCollection.Lumbaryard )
                    {
                        lumbaryard = true;
                    }
                    else if ( lumbarCol != null && lumbarCol == LumbarCollection.Trees )
                    {
                        tree = true;
                    }
                }

                if ( lumbaryard && tree )
                {
                    newGrid.put ( entry.getKey ( ), currentSquareCol );
                }
                else
                {
                    newGrid.put ( entry.getKey ( ), currentSquareCol.next ( ) );
                }
            }

        }

        return newGrid;
    }

    private static Map< Coordinate, LumbarCollection > parseInput ( final String input )
    {
        final String[] lines = splitInput ( input );

        Map< Coordinate, LumbarCollection > result = new LinkedHashMap<> ( );

        for ( int i = 0; i < lines.length; i++ )
        {
            for ( int j = 0; j < lines[ 0 ].length ( ); j++ )
            {
                result.put ( new Coordinate ( j, i ), LumbarCollection.parseCharacter ( lines[ i ].charAt ( j ) ) );
            }
        }

        return result;
    }

    private enum LumbarCollection
    {
        OpenGround ( '.' ),
        Trees ( '|' ),
        Lumbaryard ( '#' );

        private final char m_code;

        LumbarCollection ( final char code )
        {
            m_code = code;
        }

        public LumbarCollection next ( )
        {
            if ( this == OpenGround )
            {
                return Trees;
            }
            if ( this == Trees )
            {
                return Lumbaryard;
            }
            if ( this == Lumbaryard )
            {
                return OpenGround;
            }

            throw new AdventOfCodeException ( String.format ( "Unknown character %s", this ) );
        }

        public static LumbarCollection parseCharacter ( final char character )
        {
            if ( character == OpenGround.m_code )
            {
                return OpenGround;
            }
            if ( character == Trees.m_code )
            {
                return Trees;
            }
            if ( character == Lumbaryard.m_code )
            {
                return Lumbaryard;
            }

            throw new AdventOfCodeException ( String.format ( "Unknown character %s", character ) );
        }

    }
}
