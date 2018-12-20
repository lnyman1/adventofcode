package com.laranyman.aoc.util;

import com.laranyman.aoc.exceptions.AdventOfCodeException;

/**
 * @author Lara
 */
public enum Direction
{
    North ( 'N' ),
    South ( 'S' ),
    East ( 'E' ),
    West ( 'W' );

    private final char m_code;

    Direction ( final char code )
    {
        m_code = code;
    }

    public static Direction parseCharacter ( final char character )
    {
        if ( character == North.m_code )
        {
            return North;
        }
        if ( character == South.m_code )
        {
            return South;
        }
        if ( character == East.m_code )
        {
            return East;
        }
        if ( character == West.m_code )
        {
            return West;
        }

        throw new AdventOfCodeException ( String.format ( "Unknown character", character ) );
    }
}
