package com.laranyman.aoc.eighteen.dayseven;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.laranyman.aoc.DayIfc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DaySeven implements DayIfc
{
    @Override
    public String partOne ( final String input )
    {
        final List< Instruction > instructions = parseInput ( input );

        Map< Character, List< Character > > dependents = Maps.newHashMap ( );

        Set< Character > allJobs = Sets.newHashSet ( );

        for ( Instruction instruction : instructions )
        {
            allJobs.add ( instruction.getDependency ( ) );
            allJobs.add ( instruction.getStepId ( ) );

            dependents.computeIfPresent ( instruction.getDependency ( ), ( k, v ) -> {
                v.add ( instruction.getStepId ( ) );
                return v;
            } );

            dependents.computeIfAbsent ( instruction.getDependency ( ), v -> {
                List< Character > dependencies = Lists.newArrayList ( );
                dependencies.add ( instruction.getStepId ( ) );
                return dependencies;
            } );
        }

        final List< Character > completed = Lists.newArrayList ( );

        List< Character > allJobsSorted = allJobs
                .stream ( )
                .sorted ( Comparator.naturalOrder ( ) )
                .collect ( Collectors.toList ( ) );

        while ( completed.size ( ) < allJobsSorted.size ( ) )
        {
            final List< Character > canJobComplete = allJobsSorted.stream ( )
                    .filter ( e -> !completed.contains ( e ) )
                    .filter ( e -> !dependents.containsKey ( e ) || dependents.get ( e ) != null && completed.containsAll ( dependents.get ( e ) ) )
                    .collect ( Collectors.toList ( ) );

            Character job = canJobComplete.get ( 0 );
            completed.add ( job );
        }

        return completed.stream ( )
                .map ( String::valueOf )
                .collect ( Collectors.joining ( "" ) );
    }

    @Override
    public String partTwo ( final String input, final long... numbers )
    {
        int numberOfWorkers = (int) numbers[ 0 ];
        int stepDuration = (int) numbers[ 1 ];

        final List< Instruction > instructions = parseInput ( input );

        Map< Character, List< Character > > dependents = Maps.newHashMap ( );

        Set< Character > allJobs = Sets.newHashSet ( );

        for ( Instruction instruction : instructions )
        {
            allJobs.add ( instruction.getDependency ( ) );
            allJobs.add ( instruction.getStepId ( ) );

            dependents.computeIfPresent ( instruction.getDependency ( ), ( k, v ) -> {
                v.add ( instruction.getStepId ( ) );
                return v;
            } );

            dependents.computeIfAbsent ( instruction.getDependency ( ), v -> {
                List< Character > dependencies = Lists.newArrayList ( );
                dependencies.add ( instruction.getStepId ( ) );
                return dependencies;
            } );
        }

        final List< Character > completed = Lists.newArrayList ( );

        List< Character > allJobsSorted = allJobs
                .stream ( )
                .sorted ( Comparator.naturalOrder ( ) )
                .collect ( Collectors.toList ( ) );

        int duration = 0;
        int workers = 0;

        Map< Character, Integer > durationPerJob = Maps.newHashMap ( );

        while ( completed.size ( ) < allJobsSorted.size ( ) )
        {
            final List< Character > canJobComplete = allJobsSorted.stream ( )
                    .filter ( e -> !( completed.contains ( e ) || durationPerJob.keySet ( ).contains ( e ) ) )
                    .filter ( e -> !dependents.containsKey ( e ) || dependents.get ( e ) != null && completed.containsAll ( dependents.get ( e ) ) )
                    .sorted ( )
                    .collect ( Collectors.toList ( ) );

            int endOfList = canJobComplete.size ( ) < numberOfWorkers - workers
                    ? canJobComplete.size ( )
                    : numberOfWorkers - workers;

            final List< Character > jobsThatCanBeDone = canJobComplete.subList ( 0, endOfList );

            workers += jobsThatCanBeDone.size ( );

            for ( Character character : jobsThatCanBeDone )
            {
                durationPerJob.put ( character, duration + calculateDuration ( character, stepDuration ) );
            }

            duration++;

            final int total = duration;

            final List< Character > jobsDone = durationPerJob.entrySet ( )
                    .stream ( )
                    .filter ( e -> e.getValue ( ) == total )
                    .map ( Map.Entry::getKey )
                    .sorted ( )
                    .collect ( Collectors.toList ( ) );

            workers -= jobsDone.size ( );
            completed.addAll ( jobsDone );
            durationPerJob.keySet ( ).removeAll ( jobsDone );
        }

        return String.valueOf ( duration );
    }

    private static int calculateDuration ( final Character character, final int stepDuration )
    {
        return character - 'A' + 1 + stepDuration;
    }

    private static List< Instruction > parseInput ( final String input )
    {
        return Arrays.stream ( splitInput ( input ) )
                .map ( Instruction::parseInput )
                .collect ( Collectors.toList ( ) );
    }
}
