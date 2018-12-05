package com.laranyman.util;

import java.util.List;

/**
 * @author Lara
 */
public final class AdventOfCodeUtil
{
    public static String[] splitInput ( final String input )
    {
        return input.split ( "\n" );
    }

    public static int mode ( final List< Integer > a )
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
}
