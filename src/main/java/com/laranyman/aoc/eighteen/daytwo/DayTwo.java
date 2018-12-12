package com.laranyman.aoc.eighteen.daytwo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.eighteen.exceptions.AdventOfCodeException;

import java.util.List;
import java.util.Map;

/**
 * @author Lara
 */
public class DayTwo implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        final String[] arr = createArray ( input );

        int twoTimes = 0;
        int threeTimes = 0;

        for ( final String entry : arr )
        {
            Map< Character, Integer > map = Maps.newHashMap ( );

            for ( int i = 0; i < entry.length ( ); i++ )
            {
                char character = entry.charAt ( i );

                map.computeIfPresent ( character, ( k, v ) -> v + 1 );
                map.putIfAbsent ( character, 1 );
            }

            boolean alreadyCountedTwo = false;
            boolean alreadyCountedThree = false;

            for ( final Map.Entry< Character, Integer > valueMap : map.entrySet ( ) )
            {
                Integer count = valueMap.getValue ( );

                if ( !alreadyCountedTwo && count == 2 )
                {
                    twoTimes++;
                    alreadyCountedTwo = true;
                }

                if ( !alreadyCountedThree && count == 3 )
                {
                    threeTimes++;
                    alreadyCountedThree = true;
                }
            }
        }

        return String.valueOf ( twoTimes * threeTimes );
    }

    @Override
    public String partTwo ( final String input )
    {
        String[] arr = createArray ( input );

        List< String > list = Lists.newArrayList ( );

        for ( String entry : arr )
        {
            for ( String listEntry : list )
            {
                StringBuilder charactersThatAreDifferent = new StringBuilder ( );

                for ( int i = 0; i < listEntry.length ( ); i++ )
                {
                    char characterInList = listEntry.charAt ( i );
                    char characterInEntry = entry.charAt ( i );

                    if ( characterInList != characterInEntry )
                    {
                        charactersThatAreDifferent.append ( characterInEntry );
                    }
                }

                if ( charactersThatAreDifferent.length ( ) == 1 )
                {
                    return entry.replace ( charactersThatAreDifferent.toString ( ), "" );
                }
            }

            list.add ( entry );
        }

        throw new AdventOfCodeException ( "Something went horribly wrong!" );
    }

    private static String[] createArray ( final String input )
    {
        return input.split ( "\n" );
    }
}
