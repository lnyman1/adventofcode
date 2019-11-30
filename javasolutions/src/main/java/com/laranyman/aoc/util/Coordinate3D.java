package com.laranyman.aoc.util;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Lara
 */
public class Coordinate3D implements Comparator< Coordinate3D >
{
    private final int m_xCoordinate;

    private final int m_yCoordinate;

    private final int m_zCoordinate;

    public Coordinate3D (
            final int xCoordinate,
            final int yCoordinate,
            final int zCoordinate )
    {
        m_xCoordinate = xCoordinate;
        m_yCoordinate = yCoordinate;
        m_zCoordinate = zCoordinate;
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

    public List< Coordinate3D > getNeighbours ( )
    {
        List< Coordinate3D > coordinates = Lists.newArrayList ( );
        coordinates.add ( new Coordinate3D ( m_xCoordinate, m_yCoordinate - 1, m_zCoordinate ) ); //up
        coordinates.add ( new Coordinate3D ( m_xCoordinate - 1, m_yCoordinate, m_zCoordinate ) ); //left
        coordinates.add ( new Coordinate3D ( m_xCoordinate + 1, m_yCoordinate, m_zCoordinate ) ); //right
        coordinates.add ( new Coordinate3D ( m_xCoordinate, m_yCoordinate + 1, m_zCoordinate ) ); //down

        coordinates.add ( new Coordinate3D ( m_xCoordinate, m_yCoordinate, m_zCoordinate + 1 ) ); //back
        coordinates.add ( new Coordinate3D ( m_xCoordinate, m_yCoordinate, m_zCoordinate - 1 ) ); //forward
        return coordinates;
    }

    public int manhattenDistance ( final Coordinate3D coordinate )
    {
        final int xCoord = Math.abs ( m_xCoordinate - coordinate.getxCoordinate ( ) );
        final int yCoord = Math.abs ( m_yCoordinate - coordinate.getyCoordinate ( ) );
        final int zCoord = Math.abs ( m_zCoordinate - coordinate.getzCoordinate ( ) );
        return xCoord + yCoord + zCoord;
    }

    public boolean isInGridBoundaries ( final Coordinate3D lowerCoords, final Coordinate3D higherCoords )
    {
        return m_xCoordinate < lowerCoords.getxCoordinate ( )
               || m_yCoordinate < lowerCoords.getyCoordinate ( )
               || m_zCoordinate < lowerCoords.getzCoordinate ( )
               || m_xCoordinate > higherCoords.getxCoordinate ( )
               || m_yCoordinate > higherCoords.getyCoordinate ( )
               || m_zCoordinate > higherCoords.getzCoordinate ( );
    }

    @Override
    public int compare ( final Coordinate3D x1, final Coordinate3D x2 )
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
        return result;
    }

    @Override
    public boolean equals ( final Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
        final Coordinate3D that = ( Coordinate3D ) o;
        return m_xCoordinate == that.m_xCoordinate &&
               m_yCoordinate == that.m_yCoordinate &&
               m_zCoordinate == that.m_zCoordinate;
    }

    @Override
    public int hashCode ( )
    {
        return Objects.hash ( m_xCoordinate, m_yCoordinate, m_zCoordinate );
    }
}
