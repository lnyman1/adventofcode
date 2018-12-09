package com.laranyman.eighteen.daynine;

import com.google.common.collect.Maps;
import com.laranyman.eighteen.DayIfc;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.laranyman.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayNine implements DayIfc
{
    @Override
    public String partOne ( final int... numbers )
    {
        List< Integer > marbles = new LinkedList<> ( );

        Map< Integer, Integer > playerScores = Maps.newHashMap ( );

        final int numberOfPlayers = numbers[ 0 ];
        final int lastMarbleValue = numbers[ 1 ];

        int playerNumber = 1;
        int currentMarbleIdx = 0;

        marbles.add ( 0 );

        for ( int i = 1; i < lastMarbleValue + 1; i++ )
        {
            if ( isMultipleOf23 ( i ) )
            {
                final int total;

                currentMarbleIdx = currentMarbleIdx - 7 < 0
                        ? marbles.size ( ) + ( currentMarbleIdx - 7 )
                        : currentMarbleIdx - 7;

                total = i + marbles.remove ( currentMarbleIdx );

                playerScores.computeIfPresent ( playerNumber, ( key, value ) -> {
                    value += total;
                    return value;
                } );
                playerScores.putIfAbsent ( playerNumber, total );
            }
            else
            {
                if ( currentMarbleIdx + 1 < marbles.size ( ) )
                {
                    currentMarbleIdx += 2;
                    marbles.add ( currentMarbleIdx, i );
                }
                else
                {
                    final int beginningIdx = marbles.size ( ) - currentMarbleIdx;
                    marbles.add ( beginningIdx, i );
                    currentMarbleIdx = beginningIdx;
                }
            }


            playerNumber = playerNumber >= numberOfPlayers
                    ? 1
                    : playerNumber + 1;
        }

        return String.valueOf ( playerScores.values ( )
                .stream ( )
                .max ( Comparator.naturalOrder ( ) ).get ( ) );
    }

    @Override
    public String partTwo ( final int... numbers )
    {
        LinkedList< Integer > marbles = new LinkedList<> ( );

        Map< Integer, Long > playerScores = Maps.newHashMap ( );

        final int numberOfPlayers = numbers[ 0 ];
        final int lastMarbleValue = numbers[ 1 ];

        int playerNumber = 0;

        for ( int i = 0; i < lastMarbleValue + 1; i++ )
        {
            if ( i == 0 )
            {
                marbles.add ( 0 );
            }
            else if ( isMultipleOf23 ( i ) )
            {
                final long total;

                rotate ( marbles, -7 );

                total = i + marbles.removeLast ( );

                playerScores.computeIfPresent ( playerNumber, ( key, value ) -> {
                    value += total;
                    return value;
                } );
                playerScores.putIfAbsent ( playerNumber, total );
            }
            else
            {
                rotate ( marbles, 2 );
                marbles.addLast ( i );
            }

            playerNumber = playerNumber >= numberOfPlayers
                    ? 1
                    : playerNumber + 1;

        }

        return String.valueOf ( playerScores.values ( )
                .stream ( )
                .max ( Comparator.naturalOrder ( ) ).get ( ) );
    }

    private static void rotate (
            final LinkedList< Integer > list,
            final int number )
    {
        if ( number == 0 )
        {
            return;
        }

        if ( number > 0 )
        {
            int count = 0;
            while ( count < number )
            {
                final Integer last = list.removeLast ( );
                list.addFirst ( last );
                count++;
            }

            return;
        }

        int count = 0;
        while ( count < Math.abs ( number ) )
        {
            final Integer first = list.removeFirst ( );
            list.addLast ( first );
            count++;
        }

        return;
    }

    private static boolean isMultipleOf23 ( final int number )
    {
        return number % 23 == 0;
    }
}
