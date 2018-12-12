package com.laranyman.aoc.eighteen.daythree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.util.Coordinate;
import com.laranyman.aoc.eighteen.exceptions.AdventOfCodeException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Lara
 */
public class DayThree implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        final Claim[] claims = parseInput ( input );

        Map< Coordinate, Integer > coordinateIntegerMap = getCoordinatesPartOne ( claims );

        return String.valueOf ( coordinateIntegerMap.values ( )
                .stream ( )
                .filter ( e -> e > 1 )
                .count ( ) );
    }

    @Override
    public String partTwo ( final String input )
    {
        final Claim[] claims = parseInput ( input );

        Map< Coordinate, List< String > > coordinateIntegerMap = getCoordinatesPartTwo ( claims );

        List< String > idsWithSingleClaims = Lists.newArrayList ( );
        List< String > idsWithMultipleClaims = Lists.newArrayList ( );

        for ( Map.Entry< Coordinate, List< String > > entry : coordinateIntegerMap.entrySet ( ) )
        {
            if ( entry.getValue ( ).size ( ) > 1 )
            {
                idsWithMultipleClaims.addAll ( entry.getValue ( ) );
            }
            else
            {
                idsWithSingleClaims.add ( entry.getValue ( ).get ( 0 ) );
            }
        }

        for ( String single : idsWithSingleClaims )
        {
            if ( !idsWithMultipleClaims.contains ( single ) )
            {
                return single;
            }
        }

        throw new AdventOfCodeException ( "Something is not right" );
    }

    private static Map< Coordinate, Integer > getCoordinatesPartOne ( final Claim[] claims )
    {
        Map< Coordinate, Integer > coordinateIntegerMap = Maps.newHashMap ( );

        for ( Claim claim : claims )
        {
            for ( int i = 0; i < claim.getWidth ( ); i++ )
            {
                for ( int j = 0; j < claim.getHeight ( ); j++ )
                {
                    final Coordinate coordinate = new Coordinate (
                            claim.getInchesFromLeft ( ) + i,
                            claim.getInchesFromTop ( ) + j );

                    coordinateIntegerMap.computeIfPresent ( coordinate, ( k, v ) -> v + 1 );
                    coordinateIntegerMap.putIfAbsent ( coordinate, 1 );
                }
            }
        }

        return coordinateIntegerMap;
    }

    private static Map< Coordinate, List< String > > getCoordinatesPartTwo ( final Claim[] claims )
    {
        Map< Coordinate, List< String > > coordinateIntegerMap = Maps.newHashMap ( );

        for ( Claim claim : claims )
        {
            for ( int i = 0; i < claim.getWidth ( ); i++ )
            {
                for ( int j = 0; j < claim.getHeight ( ); j++ )
                {
                    final Coordinate coordinate = new Coordinate (
                            claim.getInchesFromLeft ( ) + i,
                            claim.getInchesFromTop ( ) + j );

                    coordinateIntegerMap.computeIfPresent ( coordinate, ( k, v ) -> {
                        v.add ( claim.getId ( ) );
                        return v;
                    } );

                    coordinateIntegerMap.computeIfAbsent ( coordinate, ( k ) -> {
                        List< String > ids = Lists.newArrayList ( );
                        ids.add ( claim.getId ( ) );
                        return ids;
                    } );
                }
            }
        }

        return coordinateIntegerMap;
    }

    private static Claim[] parseInput ( final String input )
    {
        return Arrays.stream ( input.split ( "\n" ) )
                .map ( Claim::parseFromString )
                .toArray ( Claim[]::new );
    }
}
