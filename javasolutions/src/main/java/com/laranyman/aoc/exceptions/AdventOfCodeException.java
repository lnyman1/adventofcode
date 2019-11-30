package com.laranyman.aoc.exceptions;

/**
 * @author Lara
 */
public class AdventOfCodeException extends RuntimeException
{
    private static final long serialVersionUID = 2458671700200749670L;

    public AdventOfCodeException ( final String message )
    {
        super ( message );
    }

    public AdventOfCodeException ( final String message, final Throwable cause )
    {
        super ( message, cause );
    }

    public AdventOfCodeException ( final Throwable cause )
    {
        super ( cause );
    }
}
