package com.laranyman.aoc.eighteen.dayeight;

import java.util.List;
import java.util.Objects;

/**
 * @author Lara
 */
public class Node
{
    private final List< Node > m_childNode;

    private final List< Integer > m_metaDataEntries;

    public Node (
            final List< Node > childNode,
            final List< Integer > metaDataEntries )
    {
        m_childNode = childNode;
        m_metaDataEntries = metaDataEntries;
    }

    public List< Node > getChildNode ( )
    {
        return m_childNode;
    }

    public List< Integer > getMetaDataEntries ( )
    {
        return m_metaDataEntries;
    }

    public int sumOfMetaDataEntries ( )
    {
        int sum = 0;

        for ( Integer metaDataEntry : m_metaDataEntries )
        {
            sum += metaDataEntry;
        }

        return sum;
    }

    @Override
    public boolean equals ( final Object o )
    {
        if ( this == o ) return true;
        if ( o == null || getClass ( ) != o.getClass ( ) ) return false;
        final Node node = ( Node ) o;
        return Objects.equals ( m_childNode, node.m_childNode ) &&
               Objects.equals ( m_metaDataEntries, node.m_metaDataEntries );
    }

    @Override
    public int hashCode ( )
    {
        return Objects.hash ( m_childNode, m_metaDataEntries );
    }
}

