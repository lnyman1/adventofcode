package com.laranyman.template;

import com.laranyman.eighteen.DayIfc;

import static com.laranyman.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayN implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        parseInput ( input );
        return null;
    }

    @Override
    public String partTwo ( final String input )
    {
        parseInput ( input );
        return null;
    }

    private static String[] parseInput ( final String input )
    {
        return splitInput ( input );
    }
}
