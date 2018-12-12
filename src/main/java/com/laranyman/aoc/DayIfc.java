package com.laranyman.aoc;

/**
 * @author Lara
 */
public interface DayIfc
{
    default String partOne ( String input )
    {
        return null;
    }

    default String partOne ( long... input )
    {
        return null;
    }

    default String partOne ( String input, long... numbers )
    {
        return null;
    }

    default String partTwo ( String input )
    {
        return null;
    }

    default String partTwo ( String input, long... numbers )
    {
        return null;
    }

    default String partTwo ( long... numbers )
    {
        return null;
    }
}
