package com.laranyman.aoc.eighteen.daysixteen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.util.OpCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.laranyman.aoc.util.AdventOfCodeUtil.execute;
import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DaySixteen implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DaySixteen.class );

    @Override
    public String partOne ( final String input )
    {
        final List< Registers > registersList = parseInput ( input );

        int moreThanThree = 0;

        for ( Registers registers : registersList )
        {
            int opCodeCount = 0;

            for ( OpCode opCode : OpCode.values ( ) )
            {
                final int[] afterRegisterCalc = execute ( opCode, registers.m_instruction, registers.m_before );

                if ( Arrays.equals ( afterRegisterCalc, registers.m_after ) )
                {
                    opCodeCount++;
                }
            }

            if ( opCodeCount >= 3 )
            {
                moreThanThree++;
            }
        }

        return String.valueOf ( moreThanThree );
    }

    @Override
    public String partTwo ( final String input )
    {
        final int idx = input.lastIndexOf ( "]" );
        final String examples = input.substring ( 0, idx + 1 );
        final String program = input.substring ( idx + 1, input.length ( ) );

        final List< Registers > exampleRegisters = parseInput ( examples );

        final String[] lines = splitInput ( program );

        List< int[] > instructions = Lists.newArrayList ( );

        for ( String line : lines )
        {
            if ( line.isEmpty ( ) )
            {
                continue;
            }
            instructions.add ( Arrays.stream ( line.split ( " " ) )
                    .mapToInt ( Integer::parseInt ).toArray ( ) );
        }

        Map< Integer, List< OpCode > > maps = Maps.newHashMap ( );

        for ( Registers registers : exampleRegisters )
        {
            List< OpCode > possibleOpcodes = Lists.newArrayList ( );

            for ( OpCode opCode : OpCode.values ( ) )
            {
                final int[] afterRegisterCalc = execute ( opCode, registers.m_instruction, registers.m_before );

                if ( Arrays.equals ( afterRegisterCalc, registers.m_after ) )
                {
                    possibleOpcodes.add ( opCode );
                }
            }

            final List< OpCode > entry = maps.get ( registers.m_instruction[ 0 ] );

            if ( entry != null )
            {
                entry.removeIf ( opCodes -> !possibleOpcodes.contains ( opCodes ) );
                maps.put ( registers.m_instruction[ 0 ], entry );
            }
            else
            {
                maps.put ( registers.m_instruction[ 0 ], possibleOpcodes );
            }
        }

        while ( true )
        {
            final int count = maps.values ( )
                    .stream ( )
                    .mapToInt ( List::size )
                    .filter ( e -> e != 1 )
                    .sum ( );

            if ( count == 0 )
            {
                break;
            }

            final List< OpCode > knownOpCodes = maps.entrySet ( )
                    .stream ( )
                    .map ( Map.Entry::getValue )
                    .filter ( e -> e.size ( ) == 1 )
                    .flatMap ( Collection::stream )
                    .collect ( Collectors.toList ( ) );

            for ( Map.Entry< Integer, List< OpCode > > entry : maps.entrySet ( ) )
            {
                final List< OpCode > codes = entry.getValue ( );

                if ( codes.size ( ) > 1 )
                {
                    codes.removeAll ( knownOpCodes );
                    maps.put ( entry.getKey ( ), codes );
                }
            }

        }

        int[] register = new int[] { 0, 0, 0, 0 };

        for ( int[] instruction : instructions )
        {
            register = execute ( maps.get ( instruction[ 0 ] ).get ( 0 ), instruction, register );
        }

        return String.valueOf ( register[ 0 ] );
    }

    private static int[] parseRegister ( final String line )
    {
        final String[] numbersAsString = line.split ( "\\[" )[ 1 ].split ( "]" )[ 0 ]
                .split ( ", " );

        return Arrays.stream ( numbersAsString )
                .mapToInt ( Integer::parseInt )
                .toArray ( );
    }

    private static List< Registers > parseInput ( final String input )
    {
        final String[] lines = splitInput ( input );

        List< Registers > registers = Lists.newArrayList ( );

        for ( int i = 0; i < lines.length; i++ )
        {
            final String line = lines[ i ];

            if ( line.isEmpty ( ) )
            {
                continue;
            }

            if ( !line.startsWith ( "B" ) )
            {
                continue;
            }

            final int[] before = parseRegister ( line );

            final int[] instruction = Arrays.stream ( lines[ i + 1 ].split ( " " ) )
                    .mapToInt ( Integer::parseInt ).toArray ( );

            final int[] after = parseRegister ( lines[ i + 2 ] );

            registers.add ( new Registers ( before, after, instruction ) );
        }

        return registers;
    }

    private static final class Registers
    {
        private final int[] m_before;

        private final int[] m_after;

        private final int[] m_instruction;

        public Registers (
                final int[] before,
                final int[] after,
                final int[] instruction )
        {
            m_before = before;
            m_after = after;
            m_instruction = instruction;
        }
    }
}
