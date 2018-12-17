package com.laranyman.aoc.eighteen.dayseventeen;

import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.eighteen.dayfifteen.DayFifteen;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import com.laranyman.aoc.util.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DaySeventeen implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DaySeventeen.class );

    @Override
    public String partOne ( final String input )
    {
        final Map< Coordinate, Type > grid = parseInput ( input );

        Coordinate coordinate = new Coordinate ( 500, 0 );

        startSpring ( grid, coordinate );

        printGrid ( grid );

        final long sum = grid.entrySet ( )
                .stream ( )
                .filter ( e -> e.getValue ( ).isWater ( ) )
                .count ( );

        return String.valueOf ( sum - maxYFromInput ( input ) + 1 );
    }

    @Override
    public String partTwo ( final String input )
    {
        final Map< Coordinate, Type > grid = parseInput ( input );

        Coordinate coordinate = new Coordinate ( 500, 0 );

        startSpring ( grid, coordinate );

        printGrid ( grid );

        final long sum = grid.entrySet ( )
                .stream ( )
                .filter ( e -> e.getValue ( ) == Type.SettledWater )
                .count ( );

        return String.valueOf ( sum );
    }

    private static int maxYFromInput ( final String input )
    {
        final String[] lines = splitInput ( input );

        int maxY = Integer.MAX_VALUE;

        for ( String line : lines )
        {
            final String[] splitLine = line.split ( "," );

            final boolean isXCoord = splitLine[ 0 ].contains ( "x" );

            final int firstNum = Integer.parseInt ( splitLine[ 0 ].substring ( 2, splitLine[ 0 ].length ( ) ) );

            if ( !isXCoord && firstNum < maxY )
            {
                maxY = firstNum;
            }

            final String range = splitLine[ 1 ].trim ( );

            final boolean rangeIsXCoord = range.contains ( "x" );

            final String[] splitRange = splitLine[ 1 ].split ( "\\.\\." );
            final int firstNumber = Integer.parseInt ( splitRange[ 0 ].substring ( 3, splitRange[ 0 ].length ( ) ) );
            final int secondNumber = Integer.parseInt ( splitRange[ 1 ] );

            for ( int i = firstNumber; i < secondNumber + 1; i++ )
            {
                if ( !rangeIsXCoord && i < maxY )
                {
                    maxY = i;
                }
            }
        }
        return maxY;
    }

    private static void startSpring ( final Map< Coordinate, Type > grid, final Coordinate startCoord )
    {
        final int maxY = getMaxY ( grid );

        int x = startCoord.getxCoordinate ( );
        int y = startCoord.getyCoordinate ( );

        while ( grid.get ( new Coordinate ( x, y + 1 ) ) != Type.Clay
                && grid.get ( new Coordinate ( x, y + 1 ) ) != Type.SettledWater )
        {
            y++;

            if ( y > maxY )
            {
                return;
            }

            grid.put ( new Coordinate ( x, y ), Type.FlowingWater );
            //printGrid ( grid );
        }

        fillContainer ( grid, x, y );
    }

    private static void fillContainer ( final Map< Coordinate, Type > grid, final int x, final int y )
    {
        int downY = y;

        while ( true )
        {
            boolean left = false;
            boolean right = false;

            //find boundaries
            int leftX;

            for ( leftX = x; leftX >= 0; leftX-- )
            {
                if ( !isNotSandOrSettledWater ( grid, new Coordinate ( leftX, downY + 1 ) ) )
                {
                    left = true;
                    break;
                }

                grid.put ( new Coordinate ( leftX, downY ), Type.FlowingWater );
                //printGrid ( grid );

                if ( isNotSandOrSettledWater ( grid, new Coordinate ( leftX - 1, downY ) ) )
                {
                    break;
                }
            }

            int rightX;
            for ( rightX = x; rightX < rightX + ( getMaxX ( grid ) - getMinX ( grid ) ); rightX++ )
            {
                if ( !isNotSandOrSettledWater ( grid, new Coordinate ( rightX, downY + 1 ) ) )
                {
                    right = true;
                    break;
                }

                grid.put ( new Coordinate ( rightX, downY ), Type.FlowingWater );
                //printGrid ( grid );

                if ( isNotSandOrSettledWater ( grid, new Coordinate ( rightX + 1, downY ) ) )
                {
                    break;
                }
            }

            if ( left )
            {
                if ( grid.get ( new Coordinate ( leftX, downY ) ) != Type.FlowingWater )
                {
                    grid.put ( new Coordinate ( leftX, downY ), Type.FlowingWater );
                    startSpring ( grid, new Coordinate ( leftX, downY ) );
                }
            }
            if ( right )
            {
                if ( grid.get ( new Coordinate ( rightX, downY ) ) != Type.FlowingWater )
                {
                    grid.put ( new Coordinate ( rightX, downY ), Type.FlowingWater );
                    startSpring ( grid, new Coordinate ( rightX, downY ) );
                }
            }
            if ( left || right )
            {
                return;
            }

            for ( int i = leftX; i < rightX + 1; i++ )
            {
                grid.put ( new Coordinate ( i, downY ), Type.SettledWater );
                //printGrid ( grid );
            }

            downY--;
        }
    }

    private static boolean isNotSandOrSettledWater ( final Map< Coordinate, Type > grid, final Coordinate coordinate )
    {
        return grid.get ( coordinate ) == Type.Clay || grid.get ( coordinate ) == Type.SettledWater;
    }

    private static Map< Coordinate, Type > parseInput ( final String input )
    {
        Map< Coordinate, Type > grid = Maps.newHashMap ( );
        grid.put ( new Coordinate ( 500, 0 ), Type.Spring );

        final String[] lines = splitInput ( input );

        for ( String line : lines )
        {
            final String[] splitLine = line.split ( "," );

            int xCoord = 0;
            int yCoord = 0;

            final boolean isXCoord = splitLine[ 0 ].contains ( "x" );

            final int firstNum = Integer.parseInt ( splitLine[ 0 ].substring ( 2, splitLine[ 0 ].length ( ) ) );

            if ( isXCoord )
            {
                xCoord = firstNum;
            }
            else
            {
                yCoord = firstNum;
            }

            final String range = splitLine[ 1 ].trim ( );

            final boolean rangeIsXCoord = range.contains ( "x" );

            final String[] splitRange = splitLine[ 1 ].split ( "\\.\\." );
            final int firstNumber = Integer.parseInt ( splitRange[ 0 ].substring ( 3, splitRange[ 0 ].length ( ) ) );
            final int secondNumber = Integer.parseInt ( splitRange[ 1 ] );

            for ( int i = firstNumber; i < secondNumber + 1; i++ )
            {
                if ( rangeIsXCoord )
                {
                    grid.put ( new Coordinate ( i, yCoord ), Type.Clay );
                }
                else
                {
                    grid.put ( new Coordinate ( xCoord, i ), Type.Clay );
                }
            }
        }

        final int minX = getMinX ( grid );

        final int minY = grid.keySet ( )
                .stream ( )
                .map ( x -> x.getyCoordinate ( ) )
                .min ( Comparator.comparingInt ( e -> e ) )
                .get ( );

        final int maxX = getMaxX ( grid );

        final int maxY = getMaxY ( grid );

        for ( int i = minX - 1; i < maxX + 1; i++ )
        {
            for ( int j = minY; j < maxY + 1; j++ )
            {
                final Type exists = grid.get ( new Coordinate ( i, j ) );

                if ( exists == null )
                {
                    grid.put ( new Coordinate ( i, j ), Type.Sand );
                }
            }
        }

        return grid.entrySet ( )
                .stream ( )
                .sorted ( ( e1, e2 ) -> e1.getKey ( ).compare ( e1.getKey ( ), e2.getKey ( ) ) )
                .collect ( Collectors.toMap ( Map.Entry::getKey, Map.Entry::getValue, ( e1, e2 ) -> e1,
                        LinkedHashMap::new ) );
    }

    private static int getMaxY ( final Map< Coordinate, Type > grid )
    {
        return grid.keySet ( )
                .stream ( )
                .map ( x -> x.getyCoordinate ( ) )
                .max ( Comparator.comparingInt ( e -> e ) )
                .get ( );
    }

    private static int getMaxX ( final Map< Coordinate, Type > grid )
    {
        return grid.keySet ( )
                .stream ( )
                .map ( x -> x.getxCoordinate ( ) )
                .max ( Comparator.comparingInt ( e -> e ) )
                .get ( );
    }

    private static int getMinX ( final Map< Coordinate, Type > grid )
    {
        return grid.keySet ( )
                .stream ( )
                .map ( x -> x.getxCoordinate ( ) )
                .min ( Comparator.comparingInt ( e -> e ) )
                .get ( );
    }

    private static void printGrid ( final Map< Coordinate, Type > grid )
    {
        String line = "";
        int y = 0;

        for ( Map.Entry< Coordinate, Type > entry : grid.entrySet ( ) )
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
    }

    private static enum Type
    {
        Clay ( '#' ),
        Sand ( '.' ),
        SettledWater ( '~' ),
        FlowingWater ( '|' ),
        Spring ( '+' );

        private final char m_code;

        private Type ( final char code )
        {
            m_code = code;
        }

        public boolean isWater ( )
        {
            return this == SettledWater || this == FlowingWater;
        }

        public static Type parseCode ( final char character )
        {
            if ( character == Clay.m_code )
            {
                return Clay;
            }
            if ( character == Sand.m_code )
            {
                return Sand;
            }
            if ( character == SettledWater.m_code )
            {
                return SettledWater;
            }
            if ( character == FlowingWater.m_code )
            {
                return FlowingWater;
            }
            if ( character == Spring.m_code )
            {
                return Spring;
            }

            throw new AdventOfCodeException ( String.format ( "Unknown character %s ", character ) );
        }
    }
}
