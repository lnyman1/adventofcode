package com.laranyman.aoc.eighteen.daytwelve;

import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayTwelve implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayTwelve.class );

    @Override
    public String partOne ( final String input, final long... numbers )
    {
        final String[] in = parseInput ( input );

        Pots startState = new Pots ( 0, in[ 0 ] );

        final Map< String, String > notes = parseNotes ( in );

        final long generation = numbers[ 0 ];

        final long sum = generate ( startState, generation, notes );

        return String.valueOf ( sum );
    }

    @Override
    public String partTwo ( final String input, final long... numbers )
    {
        final String[] in = parseInput ( input );

        Pots startState = new Pots ( 0, in[ 0 ] );

        final Map< String, String > notes = parseNotes ( in );

        final long generation = numbers[ 0 ];

        final long sum = generate ( startState, generation, notes );

        return String.valueOf ( sum );
    }

    private static long generate (
            final Pots pot,
            final long generation,
            final Map< String, String > notes )
    {
        Pots previousGen = pot;
        int previousGenSum = 0;

        int diff = 0;
        int count = 0;

        for ( int i = 0; i < generation; i++ )
        {
            final Pots currentGen = nextGen ( previousGen, notes );
            final int currentGenSum = sum ( currentGen );

            final int newDiff = currentGenSum - previousGenSum;

            if ( LOGGER.isDebugEnabled ( ) )
            {
                LOGGER.debug ( i + " " + newDiff );
            }

            if ( newDiff == diff )
            {
                count++;
            }
            if ( count == 100 )
            {
                return ( generation - i ) * newDiff + previousGenSum;
            }

            previousGen = currentGen;
            previousGenSum = currentGenSum;
            diff = newDiff;
        }

        return previousGenSum;
    }

    private static Pots nextGen (
            final Pots currentPot,
            final Map< String, String > notes )
    {
        final String seqOfCurrentPot = currentPot.m_sequence;

        String extendedSequencePre = checkEdgeCasePre ( seqOfCurrentPot );

        int addedPre = extendedSequencePre.length ( ) - seqOfCurrentPot.length ( );

        String extendedSequencePost = checkEdgeCasePost ( extendedSequencePre );

        StringBuilder newState = new StringBuilder ( extendedSequencePost );

        for ( int i = 2; i < extendedSequencePost.length ( ) - 3; i++ )
        {
            final String seqToCheck = extendedSequencePost.substring ( i - 2, i + 3 );

            newState.setCharAt ( i, notes.getOrDefault ( seqToCheck, "." ).charAt ( 0 ) );
        }

        return new Pots ( addedPre + currentPot.m_negativeIndices, newState.toString ( ) );
    }

    private static String checkEdgeCasePre ( final String seqOfCurrentPot )
    {
        try
        {
            final String seq = seqOfCurrentPot.substring ( 0, 6 );

            String endSeq = seqOfCurrentPot;

            if ( seq.contains ( "#" ) )
            {
                endSeq = "." + seqOfCurrentPot;
                return checkEdgeCasePre ( endSeq );
            }

            return endSeq;
        }
        catch ( Exception ex )
        {
            throw new AdventOfCodeException ( "input : " + seqOfCurrentPot, ex );
        }

    }

    private static String checkEdgeCasePost ( final String seqOfCurrentPot )
    {
        final String seq = seqOfCurrentPot.substring ( seqOfCurrentPot.length ( ) - 6, seqOfCurrentPot.length ( ) );

        String endSeq = seqOfCurrentPot;

        if ( seq.contains ( "#" ) )
        {
            endSeq = seqOfCurrentPot + ".";
            return checkEdgeCasePost ( endSeq );
        }

        return endSeq;
    }

    private static int sum ( final Pots pots )
    {
        int index = pots.m_negativeIndices;

        Map< Integer, Character > result = Maps.newHashMap ( );

        for ( int i = 0; i < pots.m_sequence.length ( ); i++ )
        {
            result.put ( i - index, pots.m_sequence.charAt ( i ) );
        }

        return result.entrySet ( )
                .stream ( )
                .mapToInt ( e -> e.getValue ( ) == '#' ? e.getKey ( ) : 0 )
                .sum ( );
    }

    private static Map< String, String > parseNotes ( String[] array )
    {
        Map< String, String > notes = Maps.newHashMap ( );

        for ( int i = 1; i < array.length; i++ )
        {
            final String[] input = array[ i ].split ( " => " );
            notes.put ( input[ 0 ], input[ 1 ] );
        }

        return notes;
    }

    private static String[] parseInput ( final String input )
    {
        return splitInput ( input );
    }

    private static final class Pots
    {
        private final int m_negativeIndices;

        private final String m_sequence;

        public Pots ( final int negativeIndices, final String sequence )
        {
            m_negativeIndices = negativeIndices;
            m_sequence = sequence;
        }
    }
}
