package com.laranyman.aoc.eighteen.dayfour;

import java.util.Objects;

/**
 * @author Lara
 */
public class Event implements Comparable< Event >
{
    private final int m_year;

    private final int m_month;

    private final int m_day;

    private final int m_hour;

    private final int m_minute;

    private final String m_description;

    private Event (
            final int year,
            final int month,
            final int day,
            final int hour,
            final int minute,
            final String description )
    {
        m_year = year;
        m_month = month;
        m_day = day;
        m_hour = hour;
        m_minute = minute;
        m_description = description;
    }

    public static Event parseString ( final String input )
    {
        final String[] inputSplit = input.split ( "]" );
        final String[] dateTime = input.split ( " " );
        final String[] date = dateTime[ 0 ].split ( "-" );
        final String[] time = dateTime[ 1 ].split ( ":" );
        
        return new Event (
                Integer.parseInt ( date[ 0 ].substring ( 1 ) ),
                Integer.parseInt ( date[ 1 ] ),
                Integer.parseInt ( date[ 2 ] ),
                Integer.parseInt ( time[ 0 ] ),
                Integer.parseInt ( time[ 1 ].substring ( 0, time[ 1 ].length ( ) - 1 ) ),
                inputSplit[ 1 ].trim ( ) );
    }

    public int getYear ( )
    {
        return m_year;
    }

    public int getMonth ( )
    {
        return m_month;
    }

    public int getDay ( )
    {
        return m_day;
    }

    public int getHour ( )
    {
        return m_hour;
    }

    public int getMinute ( )
    {
        return m_minute;
    }

    public String getDescription ( )
    {
        return m_description;
    }

    public boolean guardIdExists ( )
    {
        final int index = m_description.indexOf ( "#" );
        return index != -1;
    }

    public boolean isWakesUp ( )
    {
        return m_description.contains ( "wakes" );
    }

    public boolean isAsleep ( )
    {
        return m_description.contains ( "asleep" );
    }

    public int getGuardId ( )
    {
        final int index = m_description.indexOf ( "#" );
        if ( index == -1 )
        {
            return 0;
        }
        final String splitString = m_description.split ( "#" )[ 1 ];
        return Integer.parseInt ( splitString.split ( " " )[ 0 ] );
    }

    @Override
    public boolean equals ( final Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
        final Event event = ( Event ) o;
        return m_year == event.m_year &&
               m_month == event.m_month &&
               m_day == event.m_day &&
               m_hour == event.m_hour &&
               m_minute == event.m_minute &&
               Objects.equals ( m_description, event.m_description );
    }

    @Override
    public int hashCode ( )
    {
        return Objects.hash ( m_year, m_month, m_day, m_hour, m_minute, m_description );
    }

    @Override
    public int compareTo ( final Event eventOne )
    {
        if ( m_year < eventOne.m_year )
        {
            return -1;
        }

        if ( m_year > eventOne.m_year )
        {
            return 1;
        }

        if ( m_month < eventOne.m_month )
        {
            return -1;
        }

        if ( m_month > eventOne.m_month )
        {
            return 1;
        }

        if ( m_day < eventOne.m_day )
        {
            return -1;
        }

        if ( m_day > eventOne.m_day )
        {
            return 1;
        }

        if ( m_hour < eventOne.m_hour )
        {
            return -1;
        }

        if ( m_hour > eventOne.m_hour )
        {
            return 1;
        }

        if ( m_minute < eventOne.m_minute )
        {
            return -1;
        }

        if ( m_minute > eventOne.m_minute )
        {
            return 1;
        }
        return 0;
    }
}
