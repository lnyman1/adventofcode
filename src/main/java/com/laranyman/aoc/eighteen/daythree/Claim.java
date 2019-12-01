package com.laranyman.aoc.eighteen.daythree;

/**
 * @author Lara
 */
public class Claim
{
    private final String m_id;

    private final int m_inchesFromLeft;

    private final int m_inchesFromTop;

    private final int m_width;

    private final int m_height;

    public Claim (
            final String id,
            final int inchesFromLeft,
            final int inchesFromTop,
            final int width,
            final int height )
    {
        m_id = id;
        m_inchesFromLeft = inchesFromLeft;
        m_inchesFromTop = inchesFromTop;
        m_width = width;
        m_height = height;
    }

    public static Claim parseFromString ( final String input )
    {
        final String[] firstSplit = input.split ( " " );

        final String[] inches = firstSplit[ 2 ].split ( "," );

        final String[] dimensions = firstSplit[ 3 ].split ( "x" );

        return new Claim (
                firstSplit[ 0 ].substring ( 1 ),
                Integer.parseInt ( inches[ 0 ] ),
                Integer.parseInt ( inches[ 1 ].substring ( 0, inches[ 1 ].length ( ) - 1 ) ),
                Integer.parseInt ( dimensions[ 0 ] ),
                Integer.parseInt ( dimensions[ 1 ] ) );
    }

    public String getId ( )
    {
        return m_id;
    }

    public int getInchesFromLeft ( )
    {
        return m_inchesFromLeft;
    }

    public int getInchesFromTop ( )
    {
        return m_inchesFromTop;
    }

    public int getWidth ( )
    {
        return m_width;
    }

    public int getHeight ( )
    {
        return m_height;
    }
}
