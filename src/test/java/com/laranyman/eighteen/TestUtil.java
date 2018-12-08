package com.laranyman.eighteen;

import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public final class TestUtil
{
    public static void assertEqual ( final int expectedInput, final String expectedAnswer )
    {
        assertEquals ( expectedInput, Integer.parseInt ( expectedAnswer ) );
    }

    public static void assertEqual ( final String expectedInput, final String expectedAnswer )
    {
        assertEquals ( expectedInput, expectedAnswer );
    }
}
