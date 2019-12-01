package com.laranyman.aoc.eighteen.daytwentyfour;

import com.laranyman.aoc.template.DayN;
import org.junit.Test;

import static com.laranyman.aoc.TestUtil.assertEqual;

/**
 * @author Lara
 */
public class DayTwentyFourTest
{
    @Test
    public void testPartOneExampleOne ( )
    {
        String input = "Immune System:\n" +
                       "17 units each with 5390 hit points (weak to radiation, bludgeoning) with" +
                       " an attack that does 4507 fire damage at initiative 2\n" +
                       "989 units each with 1274 hit points (immune to fire; weak to bludgeoning," +
                       " slashing) with an attack that does 25 slashing damage at initiative 3\n" +
                       "\n" +
                       "Infection:\n" +
                       "801 units each with 4706 hit points (weak to radiation) with an attack" +
                       " that does 116 bludgeoning damage at initiative 1\n" +
                       "4485 units each with 2961 hit points (immune to radiation; weak to fire," +
                       " cold) with an attack that does 12 slashing damage at initiative 4";
        DayTwentyFour dayTwentyFour = new DayTwentyFour ( );
        assertEqual ( 5216, dayTwentyFour.partOne ( input ) );
    }

    @Test
    public void testPartTwoExampleOne ( )
    {
        String input = "Immune System:\n" +
                       "17 units each with 5390 hit points (weak to radiation, bludgeoning) with" +
                       " an attack that does 4507 fire damage at initiative 2\n" +
                       "989 units each with 1274 hit points (immune to fire; weak to bludgeoning," +
                       " slashing) with an attack that does 25 slashing damage at initiative 3\n" +
                       "\n" +
                       "Infection:\n" +
                       "801 units each with 4706 hit points (weak to radiation) with an attack" +
                       " that does 116 bludgeoning damage at initiative 1\n" +
                       "4485 units each with 2961 hit points (immune to radiation; weak to fire," +
                       " cold) with an attack that does 12 slashing damage at initiative 4";
        DayTwentyFour dayTwentyFour = new DayTwentyFour ( );
        assertEqual ( 51, dayTwentyFour.partTwo ( input ) );
    }
}
