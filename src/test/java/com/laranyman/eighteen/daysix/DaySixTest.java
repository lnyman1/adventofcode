package com.laranyman.eighteen.daysix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Lara
 */
public class DaySixTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "1, 1\n" +
                       "1, 6\n" +
                       "8, 3\n" +
                       "3, 4\n" +
                       "5, 5\n" +
                       "8, 9";
        assertEquals ( 17, DaySix.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "1, 1\n" +
                       "1, 6\n" +
                       "8, 3\n" +
                       "3, 4\n" +
                       "5, 5\n" +
                       "8, 9";

        assertEquals ( 16, DaySix.partTwo ( input, 32 ) );
    }

    @Test
    public void testPartOneActual ( )
    {
        String input = "357, 59\n" +
                       "312, 283\n" +
                       "130, 47\n" +
                       "89, 87\n" +
                       "87, 58\n" +
                       "158, 169\n" +
                       "182, 183\n" +
                       "300, 318\n" +
                       "82, 257\n" +
                       "200, 194\n" +
                       "71, 259\n" +
                       "112, 67\n" +
                       "82, 163\n" +
                       "107, 302\n" +
                       "58, 194\n" +
                       "40, 88\n" +
                       "288, 339\n" +
                       "64, 245\n" +
                       "243, 302\n" +
                       "41, 43\n" +
                       "147, 276\n" +
                       "143, 116\n" +
                       "103, 178\n" +
                       "262, 226\n" +
                       "253, 157\n" +
                       "313, 71\n" +
                       "202, 236\n" +
                       "353, 192\n" +
                       "96, 74\n" +
                       "167, 50\n" +
                       "125, 132\n" +
                       "90, 315\n" +
                       "174, 232\n" +
                       "185, 237\n" +
                       "126, 134\n" +
                       "152, 191\n" +
                       "104, 315\n" +
                       "283, 90\n" +
                       "95, 193\n" +
                       "252, 286\n" +
                       "48, 166\n" +
                       "69, 75\n" +
                       "48, 349\n" +
                       "59, 124\n" +
                       "334, 95\n" +
                       "263, 134\n" +
                       "50, 314\n" +
                       "196, 66\n" +
                       "342, 221\n" +
                       "60, 217";

        System.out.println ( "Part One Answer " + DaySix.partOne ( input ) );
    }

    @Test
    public void testPartTwoActual ( )
    {
        String input = "357, 59\n" +
                       "312, 283\n" +
                       "130, 47\n" +
                       "89, 87\n" +
                       "87, 58\n" +
                       "158, 169\n" +
                       "182, 183\n" +
                       "300, 318\n" +
                       "82, 257\n" +
                       "200, 194\n" +
                       "71, 259\n" +
                       "112, 67\n" +
                       "82, 163\n" +
                       "107, 302\n" +
                       "58, 194\n" +
                       "40, 88\n" +
                       "288, 339\n" +
                       "64, 245\n" +
                       "243, 302\n" +
                       "41, 43\n" +
                       "147, 276\n" +
                       "143, 116\n" +
                       "103, 178\n" +
                       "262, 226\n" +
                       "253, 157\n" +
                       "313, 71\n" +
                       "202, 236\n" +
                       "353, 192\n" +
                       "96, 74\n" +
                       "167, 50\n" +
                       "125, 132\n" +
                       "90, 315\n" +
                       "174, 232\n" +
                       "185, 237\n" +
                       "126, 134\n" +
                       "152, 191\n" +
                       "104, 315\n" +
                       "283, 90\n" +
                       "95, 193\n" +
                       "252, 286\n" +
                       "48, 166\n" +
                       "69, 75\n" +
                       "48, 349\n" +
                       "59, 124\n" +
                       "334, 95\n" +
                       "263, 134\n" +
                       "50, 314\n" +
                       "196, 66\n" +
                       "342, 221\n" +
                       "60, 217";

        System.out.println ( "Part Two Answer " + DaySix.partTwo ( input, 10000 ) );
    }
}
