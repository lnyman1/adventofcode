package com.laranyman.aoc.eighteen.dayseven;

/**
 * @author Lara
 */
public class Instruction
{
    private final Character m_stepId;

    private final Character m_dependency;

    private Instruction ( final Character stepId, final Character dependency )
    {
        m_stepId = stepId;
        m_dependency = dependency;
    }

    public static Instruction parseInput ( final String input )
    {
        char id = 0;
        char dependency = 0;

        for ( int i = 0; i < input.length ( ); i++ )
        {
            if ( i == 0 )
            {
                continue;
            }

            final char character = input.charAt ( i );

            if ( Character.isUpperCase ( character ) )
            {
                if ( id == 0 )
                {
                    id = character;
                }
                else
                {
                    dependency = character ;
                }
            }
        }

        return new Instruction ( id, dependency );
    }

    public char getStepId ( )
    {
        return m_stepId;
    }

    public char getDependency ( )
    {
        return m_dependency;
    }
}
