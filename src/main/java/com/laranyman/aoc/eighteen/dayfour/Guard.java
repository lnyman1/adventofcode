package com.laranyman.aoc.eighteen.dayfour;

import java.util.List;

/**
 * @author Lara
 */
public class Guard
{
    private final int m_id;

    private final List<Integer> m_minutesAsleep;

    public Guard ( final int id, final List<Integer> minutesAsleep )
    {
        m_id = id;
        m_minutesAsleep = minutesAsleep;
    }

    public int getId ( )
    {
        return m_id;
    }

    public List< Integer > getMinutesAsleep ( )
    {
        return m_minutesAsleep;
    }
}
