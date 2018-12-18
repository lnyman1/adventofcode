package com.laranyman.aoc.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import com.sun.imageio.plugins.gif.GIFImageWriter;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Converted {@link 'https://github.com/jawhiting/aoc2018/blob/master/src/GridImage.kt'} to Java
 *
 * @author Lara
 */
public class GridVisualisation
{
    private final int m_cellSize;

    private final Color m_defaultColour;

    private final Map< Coordinate, Color > m_colourGrid;

    public GridVisualisation ( final int cellSize, final Color color )
    {
        m_cellSize = cellSize;
        m_defaultColour = color;
        m_colourGrid = Maps.newHashMap ( );
    }

    public void add ( final Coordinate coordinate, final Color colour )
    {
        m_colourGrid.put ( coordinate, colour );
    }

    @FunctionalInterface
    public interface Transform < T >
    {
        Color invoke ( T value );
    }

    public < T > void addAll ( final Map< Coordinate, T > grid, final Transform< T > transformer )
    {
        grid.entrySet ( )
                .stream ( )
                .forEach ( coordinateTEntry -> m_colourGrid.put ( coordinateTEntry.getKey ( ),
                        transformer.invoke ( coordinateTEntry.getValue ( ) ) ) );
    }

    public BufferedImage toImage ( )
    {
        if ( m_colourGrid.isEmpty ( ) )
        {
            throw new IllegalStateException ( "No data" );
        }

        final int minX = m_colourGrid.keySet ( )
                .stream ( )
                .mapToInt ( Coordinate::getxCoordinate )
                .min ( ).getAsInt ( );

        final int maxX = m_colourGrid.keySet ( )
                .stream ( )
                .mapToInt ( Coordinate::getxCoordinate )
                .max ( ).getAsInt ( );

        final int minY = m_colourGrid.keySet ( )
                .stream ( )
                .mapToInt ( Coordinate::getyCoordinate )
                .min ( ).getAsInt ( );

        final int maxY = m_colourGrid.keySet ( )
                .stream ( )
                .mapToInt ( Coordinate::getyCoordinate )
                .max ( ).getAsInt ( );

        final int cellSize3d = m_cellSize + 1;

        final BufferedImage image = new BufferedImage ( ( maxX - minX ) * cellSize3d, ( maxY - minY ) * cellSize3d,
                BufferedImage.TYPE_INT_RGB );

        final Graphics2D graphics = image.createGraphics ( );

        if ( graphics != null )
        {
            graphics.setBackground ( m_defaultColour );
            graphics.clearRect ( 0, 0, image.getWidth ( ) * cellSize3d, image.getHeight ( ) * cellSize3d );
        }

        m_colourGrid.forEach ( ( ( coordinate, color ) ->
        {
            graphics.setColor ( color );
            graphics.fill3DRect ( coordinate.getxCoordinate ( ) * cellSize3d,
                    coordinate.getyCoordinate ( ) * cellSize3d,
                    cellSize3d, cellSize3d, true );
            graphics.draw3DRect ( coordinate.getxCoordinate ( ) * cellSize3d,
                    coordinate.getyCoordinate ( ) * cellSize3d, cellSize3d, cellSize3d, true );
        } ) );

        return image;
    }

    public void write ( final String fileName ) throws IOException
    {
        ImageIO.write ( toImage ( ), "gif", new File ( fileName ) );
    }

    public static void animate ( final List< BufferedImage > frames, final String fileName, final int rateMillis ) throws IOException
    {
        final FileImageOutputStream fileOutputStream = new FileImageOutputStream ( new File ( fileName ) );

        final GifSequenceWriter writer = new GifSequenceWriter ( fileOutputStream, frames.get ( 0 ).getType ( ),
                rateMillis, true );

        frames.forEach ( gifSeq -> {
            try
            {
                writer.writeToSequence ( gifSeq );
            }
            catch ( IOException e )
            {
                throw new AdventOfCodeException ( "", e );
            }
        } );

        writer.close ( );
        fileOutputStream.close ( );
    }

    public static void main ( String[] args ) throws IOException
    {
        List< BufferedImage > frames = Lists.newArrayList ( );

        for ( int i = 0; i < 10; i++ )
        {
            final GridVisualisation grid = new GridVisualisation ( 20, Color.white );

            for ( int x = 0; x < 3; x++ )
            {
                for ( int y = 0; y < 3; y++ )
                {
                    Color color = ( x + y + i ) % 2 == 0 ? Color.BLUE : Color.YELLOW;
                    grid.add ( new Coordinate ( x, y ), color );
                }
            }

            frames.add ( grid.toImage ( ) );
        }

        GridVisualisation.animate (
                frames,
                "/Users/Lara/Documents/adventofcode/src/main/java/com/laranyman/aoc/util/test.gif",
                500 );
    }
}