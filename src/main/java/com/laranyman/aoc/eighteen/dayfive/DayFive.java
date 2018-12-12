package com.laranyman.aoc.eighteen.dayfive;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lara
 */
public class DayFive implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        List< String > forbiddenStrings = getForbiddenPatterns ( );
        final String result = replaceAll ( input, forbiddenStrings );
        return String.valueOf ( result.length ( ) );
    }

    @Override
    public String partTwo ( final String input )
    {
        List< String > forbiddenStrings = getForbiddenPatterns ( );

        Map< String, Integer > result = Maps.newHashMap ( );

        for ( char alphabet = 'a'; alphabet <= 'z'; alphabet++ )
        {
            String polymer = input;

            polymer = polymer.replaceAll ( String.valueOf ( alphabet ), "" );
            polymer = polymer.replaceAll ( String.valueOf ( Character.toUpperCase ( alphabet ) ), "" );
            final String resultingPolymer = replaceAll ( polymer, forbiddenStrings );
            result.put ( String.valueOf ( alphabet ), resultingPolymer.length ( ) );
        }

        final List< Integer > lowestValue = result.values ( )
                .stream ( )
                .sorted ( Comparator.naturalOrder ( ) )
                .collect ( Collectors.toList ( ) );

        return String.valueOf ( lowestValue.get ( 0 ) );
    }

    private static List< String > getForbiddenPatterns ( )
    {
        List< String > strings = Lists.newArrayList ( );

        for ( char alphabet = 'a'; alphabet <= 'z'; alphabet++ )
        {
            strings.add ( alphabet + String.valueOf ( Character.toUpperCase ( alphabet ) ) );
            strings.add ( String.valueOf ( Character.toUpperCase ( alphabet ) ) + alphabet );
        }

        return strings;
    }

    private static String replaceAll (
            final String input,
            final List< String > strings )
    {
        String polymers = input;

        for ( String forbidden : strings )
        {
            polymers = polymers.replaceAll ( forbidden, "" );
        }

        if ( polymers.length ( ) == input.length ( ) )
        {
            return polymers;
        }

        return replaceAll ( polymers, strings );
    }
}
