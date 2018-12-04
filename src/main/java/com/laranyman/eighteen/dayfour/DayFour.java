package com.laranyman.eighteen.dayfour;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Lara
 */
public class DayFour
{
    public static int partOne ( final String input )
    {
        final List< Event > sortedEvents = parseInput ( input );

        final Map< Integer, Guard > guards = sortGuards ( sortedEvents );

        final List< Guard > sortedGuards = guards.values ( )
                .stream ( )
                .sorted ( Comparator.comparingInt ( e -> e.getMinutesAsleep ( ).size ( ) ) )
                .collect ( Collectors.toList ( ) );

        final Guard mostAsleep = sortedGuards.get ( sortedGuards.size ( ) - 1 );

        final int mode = mode ( mostAsleep.getMinutesAsleep ( ) );

        return mostAsleep.getId ( ) * mode;

    }

    public static int partTwo ( final String input )
    {
        final List< Event > sortedEvents = parseInput ( input );

        final Map< Integer, Guard > guards = sortGuards ( sortedEvents );

        final List< Guard > sortedGuards = guards.values ( )
                .stream ( )
                .sorted ( Comparator.comparingInt ( e -> mode ( e.getMinutesAsleep ( ) ) ) )
                .collect ( Collectors.toList ( ) );

        final Guard mostAsleep = sortedGuards.get ( sortedGuards.size ( ) - 1 );

        final int mode = mode ( mostAsleep.getMinutesAsleep ( ) );

        return mostAsleep.getId ( ) * mode;
    }

    private static int mode ( final List< Integer > a )
    {
        int maxValue = 0;
        int maxCount = 0;

        for ( int i = 0; i < a.size ( ); ++i )
        {
            int count = 0;
            for ( int integer : a )
            {
                if ( integer == a.get ( i ) )
                {
                    ++count;
                }
            }
            if ( count > maxCount )
            {
                maxCount = count;
                maxValue = a.get ( i );
            }
        }

        return maxValue;
    }

    private static Map< Integer, Guard > sortGuards ( final List< Event > sortedEvents )
    {
        Map< Integer, Guard > guards = Maps.newHashMap ( );

        Guard guardOnDuty = null;
        int minuteAsleep = 0;

        for ( Event event : sortedEvents )
        {
            if ( event.guardIdExists ( ) )
            {
                guardOnDuty = guards.get ( event.getGuardId ( ) );

                if ( guardOnDuty == null )
                {
                    guardOnDuty = new Guard ( event.getGuardId ( ), new ArrayList<> ( ) );
                }
            }

            if ( event.isAsleep ( ) )
            {
                minuteAsleep = event.getMinute ( );
            }

            if ( event.isWakesUp ( ) )
            {
                final List< Integer > minutes = guardOnDuty.getMinutesAsleep ( );

                for ( int i = minuteAsleep; i < event.getMinute ( ); i++ )
                {
                    minutes.add ( i );
                }
                guards.put ( guardOnDuty.getId ( ), new Guard ( guardOnDuty.getId ( ), minutes ) );
            }
        }

        return guards;
    }

    private static List< Event > parseInput ( final String input )
    {
        return Arrays.stream ( input.split ( "\n" ) )
                .map ( Event::parseString )
                .sorted ( Comparator.naturalOrder ( ) )
                .collect ( Collectors.toList ( ) );
    }
}
