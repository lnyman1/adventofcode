package com.laranyman.aoc.util;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Lara
 */
public class Coordinate4D implements Comparator< Coordinate4D >
{
    private final int m_tCoordinate;

    private final int m_xCoordinate;

    private final int m_yCoordinate;

    private final int m_zCoordinate;

    public Coordinate4D (
            final int tCoordinate,
            final int xCoordinate,
            final int yCoordinate,
            final int zCoordinate )
    {
        m_tCoordinate = tCoordinate;
        m_xCoordinate = xCoordinate;
        m_yCoordinate = yCoordinate;
        m_zCoordinate = zCoordinate;
    }

    public int gettCoordinate ( )
    {
        return this.m_tCoordinate;
    }

    public int getxCoordinate ( )
    {
        return m_xCoordinate;
    }

    public int getyCoordinate ( )
    {
        return m_yCoordinate;
    }

    public int getzCoordinate ( )
    {
        return m_zCoordinate;
    }

    public List< Coordinate4D > getNeighbours ( )
    {
        List< Coordinate4D > coordinates = Lists.newArrayList ( );
        coordinates.add ( new Coordinate4D ( m_tCoordinate, m_xCoordinate, m_yCoordinate - 1, m_zCoordinate ) );
        coordinates.add ( new Coordinate4D ( m_tCoordinate, m_xCoordinate - 1, m_yCoordinate, m_zCoordinate ) );
        coordinates.add ( new Coordinate4D ( m_tCoordinate, m_xCoordinate + 1, m_yCoordinate, m_zCoordinate ) );
        coordinates.add ( new Coordinate4D ( m_tCoordinate, m_xCoordinate, m_yCoordinate + 1, m_zCoordinate ) );

        coordinates.add ( new Coordinate4D ( m_tCoordinate, m_xCoordinate, m_yCoordinate, m_zCoordinate + 1 ) );
        coordinates.add ( new Coordinate4D ( m_tCoordinate, m_xCoordinate, m_yCoordinate, m_zCoordinate - 1 ) );
        coordinates.add ( new Coordinate4D ( m_tCoordinate + 1, m_xCoordinate, m_yCoordinate, m_zCoordinate ) );
        coordinates.add ( new Coordinate4D ( m_tCoordinate - 1, m_xCoordinate, m_yCoordinate, m_zCoordinate ) );
        return coordinates;
    }

    public int manhattenDistance ( final Coordinate4D coordinate )
    {
        final int tCoord = Math.abs ( m_tCoordinate - coordinate.gettCoordinate ( ) );
        final int xCoord = Math.abs ( m_xCoordinate - coordinate.getxCoordinate ( ) );
        final int yCoord = Math.abs ( m_yCoordinate - coordinate.getyCoordinate ( ) );
        final int zCoord = Math.abs ( m_zCoordinate - coordinate.getzCoordinate ( ) );
        return tCoord + xCoord + yCoord + zCoord;
    }

    public boolean isInGridBoundaries ( final Coordinate4D lowerCoords, final Coordinate4D higherCoords )
    {
        return m_xCoordinate < lowerCoords.getxCoordinate ( )
               || m_yCoordinate < lowerCoords.getyCoordinate ( )
               || m_zCoordinate < lowerCoords.getzCoordinate ( )
               || m_tCoordinate < lowerCoords.gettCoordinate ( )
               || m_xCoordinate > higherCoords.getxCoordinate ( )
               || m_yCoordinate > higherCoords.getyCoordinate ( )
               || m_zCoordinate > higherCoords.getzCoordinate ( )
               || m_tCoordinate > higherCoords.gettCoordinate ( );
    }

    @Override
    public int compare ( final Coordinate4D x1, final Coordinate4D x2 )
    {
        int result = Double.compare ( x1.getzCoordinate ( ), x2.getzCoordinate ( ) );
        if ( result != 0 )
        {
            return result;
        }
        result = Double.compare ( x1.getyCoordinate ( ), x2.getyCoordinate ( ) );
        if ( result == 0 )
        {
            result = Double.compare ( x1.getxCoordinate ( ), x2.getxCoordinate ( ) );
        }
        if ( result == 0 )
        {
            result = Double.compare ( x1.gettCoordinate ( ), x2.gettCoordinate ( ) );
        }
        return result;
    }

    @Override
    public boolean equals ( final Object o )
    {
        if ( this == o ) return true;
        if ( o == null || this.getClass ( ) != o.getClass ( ) ) return false;
        final Coordinate4D that = ( Coordinate4D ) o;
        return this.m_tCoordinate == that.m_tCoordinate &&
               this.m_xCoordinate == that.m_xCoordinate &&
               this.m_yCoordinate == that.m_yCoordinate &&
               this.m_zCoordinate == that.m_zCoordinate;
    }

    @Override
    public int hashCode ( )
    {
        return Objects.hash ( this.m_tCoordinate, this.m_xCoordinate, this.m_yCoordinate, this.m_zCoordinate );
    }
}
