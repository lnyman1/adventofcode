package com.laranyman.eighteen.dayone;

import com.google.common.collect.Lists;
import com.laranyman.eighteen.DayIfc;

import java.util.Arrays;
import java.util.List;

/**
 * @author Lara
 */
public class DayOne implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        int[] numbers = createArray ( input );

        int sum = 0;

        for ( int num : numbers )
        {
            sum += num;
        }

        return String.valueOf ( sum );
    }

    @Override
    public String partTwo ( final String input )
    {
        List< Integer > knownFrequencies = Lists.newArrayList ( );
        knownFrequencies.add ( 0 );

        int[] numbers = createArray ( input );

        return String.valueOf ( repeat ( numbers, knownFrequencies, 0 ) );
    }

    private static int repeat (
            final int[] numbers,
            final List< Integer > knownFrequencies,
            final int sum )
    {
        int total = sum;

        for ( int num : numbers )
        {
            total += num;

            if ( knownFrequencies.contains ( total ) )
            {
                return total;
            }

            knownFrequencies.add ( total );
        }

        return repeat ( numbers, knownFrequencies, total );
    }

    private static int[] createArray ( final String input )
    {
        String[] arr = input.split ( "\n" );

        return Arrays.stream ( arr )
                .mapToInt ( Integer::parseInt )
                .toArray ( );
    }
}
