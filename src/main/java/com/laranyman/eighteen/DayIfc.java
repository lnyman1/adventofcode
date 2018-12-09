package com.laranyman.eighteen;

/**
 * @author Lara
 */
public interface DayIfc
{
    default String partOne ( String input )
    {
        return null;
    }

    default String partOne ( int ... input )
    {
        return null;
    }

    default String partTwo ( String input )
    {
        return null;
    }

    default String partTwo ( String input, int... numbers )
    {
        return null;
    }

    default String partTwo ( int... numbers )
    {
        return null;
    }
}
