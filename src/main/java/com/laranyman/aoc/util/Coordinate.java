package com.laranyman.aoc.util;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Lara
 */
public class Coordinate implements Comparator< Coordinate >
{
    private final int m_xCoordinate;

    private final int m_yCoordinate;

    public Coordinate (
            final int xCoordinate,
            final int yCoordinate )
    {
        m_xCoordinate = xCoordinate;
        m_yCoordinate = yCoordinate;
    }

    public int getxCoordinate ( )
    {
        return m_xCoordinate;
    }

    public int getyCoordinate ( )
    {
        return m_yCoordinate;
    }

    public List< Coordinate > getNeighbours ( )
    {
        List< Coordinate > coordinates = Lists.newArrayList ( );
        coordinates.add ( new Coordinate ( m_xCoordinate, m_yCoordinate - 1 ) );
        coordinates.add ( new Coordinate ( m_xCoordinate - 1, m_yCoordinate ) );
        coordinates.add ( new Coordinate ( m_xCoordinate + 1, m_yCoordinate ) );
        coordinates.add ( new Coordinate ( m_xCoordinate, m_yCoordinate + 1 ) );
        return coordinates;
    }

    public List< Coordinate > getEightNeighbours ( )
    {
        List< Coordinate > coordinates = Lists.newArrayList ( );
        coordinates.addAll ( getNeighbours ( ) );
        coordinates.add ( new Coordinate ( m_xCoordinate - 1, m_yCoordinate - 1 ) );
        coordinates.add ( new Coordinate ( m_xCoordinate - 1, m_yCoordinate + 1 ) );
        coordinates.add ( new Coordinate ( m_xCoordinate + 1, m_yCoordinate - 1 ) );
        coordinates.add ( new Coordinate ( m_xCoordinate + 1, m_yCoordinate + 1 ) );
        return coordinates;
    }

    public Coordinate getLeftCoordinate ( )
    {
        return new Coordinate ( m_xCoordinate - 1, m_yCoordinate );
    }

    public Coordinate getRightCoordinate ( )
    {
        return new Coordinate ( m_xCoordinate + 1, m_yCoordinate );
    }

    public Coordinate getUpCoordinate ( )
    {
        return new Coordinate ( m_xCoordinate, m_yCoordinate - 1 );
    }

    public Coordinate getDownCoordinate ( )
    {
        return new Coordinate ( m_xCoordinate, m_yCoordinate + 1 );
    }

    public boolean isInGridBoundaries ( final Coordinate lowerCoords, final Coordinate higherCoords )
    {
        return m_xCoordinate < lowerCoords.getxCoordinate ( )
               || m_yCoordinate < lowerCoords.getyCoordinate ( )
               || m_xCoordinate > higherCoords.getxCoordinate ( )
               || m_yCoordinate > higherCoords.getyCoordinate ( );
    }

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

    @Override
    public boolean equals ( final Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
        final Coordinate that = ( Coordinate ) o;
        return m_xCoordinate == that.m_xCoordinate &&
               m_yCoordinate == that.m_yCoordinate;
    }

    @Override
    public int hashCode ( )
    {
        return Objects.hash ( m_xCoordinate, m_yCoordinate );
    }
}
