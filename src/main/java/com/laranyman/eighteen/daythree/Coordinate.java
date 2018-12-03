package com.laranyman.eighteen.daythree;

import java.util.Objects;

/**
 * @author Lara
 */
public class Coordinate
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
