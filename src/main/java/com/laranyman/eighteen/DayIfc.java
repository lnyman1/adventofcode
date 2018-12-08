package com.laranyman.eighteen;

/**
 * @author Lara
 */
public interface DayIfc
{
    String partOne ( String input );

    default String partTwo ( String input )
    {
        return null;
    }

    default String partTwo ( String input, int... numbers )
    {
        return null;
    }
}
