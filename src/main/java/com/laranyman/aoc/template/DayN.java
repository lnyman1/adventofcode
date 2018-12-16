package com.laranyman.aoc.template;

import com.laranyman.aoc.DayIfc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayN implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayN.class );

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
