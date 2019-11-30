package com.laranyman.aoc.eighteen.daynineteen;

import com.google.common.collect.Lists;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.util.OpCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.IntStream;

import static com.laranyman.aoc.util.AdventOfCodeUtil.execute;
import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayNineteen implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayNineteen.class );

    @Override
    public String partOne ( final String input, final long... numbers )
    {
        int instructionPointerRegister = ( int ) numbers[ 0 ];

        int instructionPointer = 0;

        final List< Instruction > instructions = parseInput ( input );

        int count = 0;

        int[] beforeRegister = new int[] { 0, 0, 0, 0, 0, 0 };

        while ( instructionPointer < instructions.size ( ) )
        {
            count++;

            final Instruction instruction = instructions.get ( instructionPointer );

            beforeRegister[ instructionPointerRegister ] = instructionPointer;

            final int[] afterRegisterCalc = execute ( instruction.m_opcodes, instruction.m_instruction,
                    beforeRegister );

            beforeRegister = afterRegisterCalc;

            instructionPointer = beforeRegister[ instructionPointerRegister ] + 1;

            //printRegister ( count, beforeRegister );
        }

        return String.valueOf ( beforeRegister[ 0 ] );

    }

    @Override
    public String partTwo ( final String input, final long... numbers )
    {
        int instructionPointerRegister = ( int ) numbers[ 0 ];

        int instructionPointer = 0;

        final List< Instruction > instructions = parseInput ( input );

        int[] beforeRegister = new int[] { 1, 0, 0, 0, 0, 0 };

        int count = 0;

        while ( instructionPointer < instructions.size ( ) )
        {
            count++;

            final Instruction instruction = instructions.get ( instructionPointer );

            beforeRegister[ instructionPointerRegister ] = instructionPointer;

            final int[] afterRegisterCalc = execute ( instruction.m_opcodes, instruction.m_instruction,
                    beforeRegister );

            beforeRegister = afterRegisterCalc;

            if ( beforeRegister[ instructionPointerRegister ] == 10 )
            {
                break;
            }

            instructionPointer = beforeRegister[ instructionPointerRegister ] + 1;

            //printRegister ( count, beforeRegister );
        }

        return String.valueOf ( sum ( beforeRegister[ 2 ] ) );

    }

    private static void printRegister ( final int count, final int[] register )
    {
        LOGGER.info ( count + "   " + StringUtils.join ( ArrayUtils.toObject ( register ), ", " ) );
    }

    private static int sum ( int n )
    {
        return IntStream.range ( 1, n + 1 )
                .filter ( x -> n % x == 0 )
                .sum ( );
    }

    private static List< Instruction > parseInput ( final String input )
    {
        final String[] lines = splitInput ( input );

        List< Instruction > instructions = Lists.newArrayList ( );

        for ( int i = 0; i < lines.length; i++ )
        {
            final String[] lineSplit = lines[ i ].split ( " " );

            int[] instruction = new int[ 4 ];
            //so i can use util without refactoring
            instruction[ 0 ] = 0;
            instruction[ 1 ] = Integer.parseInt ( lineSplit[ 1 ] );
            instruction[ 2 ] = Integer.parseInt ( lineSplit[ 2 ] );
            instruction[ 3 ] = Integer.parseInt ( lineSplit[ 3 ] );

            instructions.add ( new Instruction ( OpCode.valueOf ( lineSplit[ 0 ] ),
                    instruction ) );
        }

        return instructions;
    }

    private static final class Instruction
    {
        private final OpCode m_opcodes;

        private final int[] m_instruction;

        public Instruction ( final OpCode opcodes, final int[] instruction )
        {
            m_opcodes = opcodes;
            m_instruction = instruction;
        }
    }
}
