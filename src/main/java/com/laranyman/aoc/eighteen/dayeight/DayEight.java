package com.laranyman.aoc.eighteen.dayeight;

import com.google.common.collect.Lists;
import com.laranyman.aoc.DayIfc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author Lara
 */
public class DayEight implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        final Queue< Integer > licenseFile = parseInput ( input );

        List< Node > nodes = Lists.newArrayList ( );

        while ( !licenseFile.isEmpty ( ) )
        {
            nodes.add ( createTree ( licenseFile ) );
        }

        return String.valueOf ( sumMetaData ( nodes ) );
    }

    @Override
    public String partTwo ( final String input )
    {
        final Queue< Integer > licenseFile = parseInput ( input );

        List< Node > nodes = Lists.newArrayList ( );

        while ( !licenseFile.isEmpty ( ) )
        {
            nodes.add ( createTree ( licenseFile ) );
        }

        return String.valueOf ( sumOfIndexes ( nodes ) );
    }

    private static int sumOfIndexes ( final List< Node > nodes )
    {
        int sum = 0;

        for ( final Node node : nodes )
        {
            if ( !node.getChildNode ( ).isEmpty ( ) )
            {
                List< Node > childIndexes = Lists.newArrayList ( );

                final List< Integer > indexes = node.getMetaDataEntries ( );

                for ( Integer index : indexes )
                {
                    final int idx = index - 1;

                    if ( idx < 0 || idx >= node.getChildNode ( ).size ( ) )
                    {
                        continue;
                    }
                    else
                    {
                        final Node childNode = node.getChildNode ( ).get ( idx );
                        childIndexes.add ( childNode );
                    }
                }

                sum += sumOfIndexes ( childIndexes );
            }
            else
            {
                sum += node.sumOfMetaDataEntries ( );
            }
        }

        return sum;
    }

    private static int sumMetaData ( final List< Node > nodes )
    {
        int sum = 0;

        for ( Node node : nodes )
        {
            sum += node.sumOfMetaDataEntries ( );
            sum += sumMetaData ( node.getChildNode ( ) );
        }

        return sum;
    }

    private static Node createTree ( final Queue< Integer > licenseFile )
    {
        List< Node > childNodes = Lists.newArrayList ( );

        final int numberOfChildNodes = licenseFile.remove ( );
        final int numberOfMetaData = licenseFile.remove ( );

        for ( int i = 0; i < numberOfChildNodes; i++ )
        {
            childNodes.add ( createTree ( licenseFile ) );
        }

        final List< Integer > metaData = Lists.newArrayList ( );

        for ( int i = 0; i < numberOfMetaData; i++ )
        {
            metaData.add ( licenseFile.remove ( ) );
        }

        return new Node ( childNodes, metaData );
    }


    private static Queue< Integer > parseInput ( final String input )
    {
        return Arrays.stream ( input.split ( " " ) )
                .map ( Integer::parseInt )
                .collect ( Collectors.toCollection ( LinkedList::new ) );
    }
}
