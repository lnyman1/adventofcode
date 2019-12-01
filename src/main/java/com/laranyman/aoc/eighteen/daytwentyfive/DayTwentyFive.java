package com.laranyman.aoc.eighteen.daytwentyfive;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.util.Coordinate4D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayTwentyFive implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayTwentyFive.class );

    @Override
    public String partOne ( final String input )
    {
        final List< Coordinate4D > coordinates = parseInput ( input );

        int constellations = 0;

        Set< Coordinate4D > visitedAll = Sets.newHashSet ( );

        while ( true )
        {
            Set< Coordinate4D > visited = Sets.newHashSet ( );

            List< Coordinate4D > toVisit = Lists.newArrayList ( );

            for ( Coordinate4D coord : coordinates )
            {
                if ( !visitedAll.contains ( coord ) )
                {
                    toVisit.add ( coord );
                    break;
                }
            }

            if ( toVisit.isEmpty ( ) )
            {
                break;
            }

            while ( !toVisit.isEmpty ( ) )
            {
                final Coordinate4D current = toVisit.remove ( 0 );
                visited.add ( current );

                for ( Coordinate4D coordinate4D : coordinates )
                {
                    if ( !visited.contains ( coordinate4D ) && !visitedAll.contains ( coordinate4D ) )
                    {
                        final int dist = current.manhattenDistance ( coordinate4D );

                        if ( dist <= 3 )
                        {
                            toVisit.add ( coordinate4D );
                        }
                    }
                }
            }

            visitedAll.addAll ( visited );
            constellations += 1;
        }

        return String.valueOf ( constellations );
    }

    @Override
    public String partTwo ( final String input )
    {
        return "Finished Advent of Code!";
    }

    private static List< Coordinate4D > parseInput ( final String input )
    {
        List< Coordinate4D > result = Lists.newArrayList ( );

        final String[] lines = splitInput ( input );

        for ( String line : lines )
        {
            final String[] coordinates = line.split ( "," );

            result.add ( new Coordinate4D (
                    Integer.parseInt ( coordinates[ 0 ] ),
                    Integer.parseInt ( coordinates[ 1 ] ),
                    Integer.parseInt ( coordinates[ 2 ] ),
                    Integer.parseInt ( coordinates[ 3 ] ) ) );
        }

        return result;
    }
}
