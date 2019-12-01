package com.laranyman.aoc.eighteen.dayeleven;

import com.laranyman.aoc.DayIfc;

/**
 * @author Lara
 */
public class DayEleven implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        int[][] grid = createGrid ( input );

        int x = 0;
        int y = 0;

        int highest = 0;

        for ( int i = 0; i < grid.length - 2; i++ )
        {
            for ( int j = 0; j < grid.length - 2; j++ )
            {
                final int value = grid[ i ][ j ] + grid[ i ][ j + 1 ] + grid[ i ][ j + 2 ]
                                  + grid[ i + 1 ][ j ] + grid[ i + 1 ][ j + 1 ] + grid[ i + 1 ][ j + 2 ]
                                  + grid[ i + 2 ][ j ] + grid[ i + 2 ][ j + 1 ] + grid[ i + 2 ][ j + 2 ];

                if ( value > highest )
                {
                    x = i + 1;
                    y = j + 1;
                    highest = value;
                }
            }
        }

        return x + "," + y;
    }

    @Override
    public String partTwo ( final String input )
    {
        int[][] grid = createGrid ( input );

        int x = 0;
        int y = 0;

        int highest = 0;
        int sizeOfGrid = 0;

        for ( int i = 0; i < grid.length; i++ )
        {
            for ( int j = 0; j < grid.length; j++ )
            {
                for ( int s = 0; s < grid.length; s++ )
                {
                    final int value = sumOfGrid ( i, j, grid, s );

                    if ( value > highest )
                    {
                        x = i + 1;
                        y = j + 1;
                        highest = value;
                        sizeOfGrid = s;
                    }
                }
            }
        }

        return x + "," + y + "," + sizeOfGrid;
    }

    private static int sumOfGrid ( int i, int j, int[][] grid, int size )
    {
        int value = 0;

        for ( int si = 0; si < size; si++ )
        {
            for ( int sj = 0; sj < size; sj++ )
            {
                int xCoord = i + si;
                int yCoord = j + sj;

                if ( xCoord < grid.length && yCoord < grid.length )
                {
                    value += grid[ xCoord ][ yCoord ];
                }
            }
        }

        return value;
    }

    private static int[][] createGrid ( final String input )
    {
        int[][] grid = new int[ 300 ][ 300 ];

        for ( int i = 0; i < grid.length; i++ )
        {
            for ( int j = 0; j < grid[ i ].length; j++ )
            {
                final int rackId = i + 1 + 10;
                final long calculaution = ( rackId * ( j + 1 ) + Integer.parseInt ( input ) ) * rackId;
                final String numberAsString = String.valueOf ( calculaution );
                int hundreds = numberAsString.length ( ) < 3
                        ? 0
                        : Integer.parseInt ( numberAsString.substring ( numberAsString.length ( ) - 3,
                        numberAsString.length ( ) - 2 ) );
                int powerLevel = hundreds - 5;
                grid[ i ][ j ] = powerLevel;
            }
        }

        return grid;
    }
}
