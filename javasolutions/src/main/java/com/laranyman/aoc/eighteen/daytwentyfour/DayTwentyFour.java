package com.laranyman.aoc.eighteen.daytwentyfour;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.laranyman.aoc.DayIfc;
import com.laranyman.aoc.exceptions.AdventOfCodeException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.laranyman.aoc.util.AdventOfCodeUtil.splitInput;

/**
 * @author Lara
 */
public class DayTwentyFour implements DayIfc
{
    private static final Logger LOGGER = LoggerFactory.getLogger ( DayTwentyFour.class );

    private static final Pattern s_pattern =
            Pattern.compile ( "(\\d+) units each with (\\d+) hit points( \\([a-zA-Z ;,]+\\))? with an attack that " +
                              "does (\\d+) ([a-zA-Z]+) damage at initiative (\\d+)" );

    @Override
    public String partOne ( final String input )
    {
        final List< Group > groups = parseInput ( input, 0 );

        while ( true )
        {
            final Map< Integer, Integer > targets = targetSelection ( groups );

            attack ( groups, targets );

            groups.removeIf ( e -> e.getNumberOfUnits ( ) <= 0 );

            if ( numberOfArmies ( groups ) == 1 )
            {
                break;
            }
        }
        return String.valueOf ( groups.stream ( ).mapToInt ( Group::getNumberOfUnits ).sum ( ) );
    }

    @Override
    public String partTwo ( final String input )
    {
        int boost = 0;

        while ( true )
        {
            final List< Group > groups = parseInput ( input, boost );

            while ( true )
            {
                final Map< Integer, Integer > targets = targetSelection ( groups );

                final boolean unitsKilled = attack ( groups, targets );

                if ( !unitsKilled )
                {
                    break;
                }

                groups.removeIf ( e -> e.getNumberOfUnits ( ) <= 0 );

                if ( numberOfArmies ( groups ) == 1 )
                {
                    break;
                }
            }

            if ( numberOfArmies ( groups ) == 1 && groups.get ( 0 ).getArmyType ( ) == ArmyType.ImmuneSystem )
            {
                return String.valueOf ( groups.stream ( ).mapToInt ( Group::getNumberOfUnits ).sum ( ) );
            }

            boost++;
            LOGGER.info ( "Running Boost {}", boost );
        }
    }

    private static boolean attack (
            final List< Group > groups,
            final Map< Integer, Integer > targets )
    {
        boolean killed = false;

        final List< Group > sortedGroups = groups.stream ( )
                .sorted ( Comparator.comparingInt ( e -> -e.getInitiative ( ) ) )
                .collect ( Collectors.toList ( ) );

        for ( Group group : sortedGroups )
        {
            int index = -1;

            for ( int i = 0; i < groups.size ( ); i++ )
            {
                final Group groupInList = groups.get ( i );

                if ( groupInList.getInitiative ( ) == group.getInitiative ( )
                     && groupInList.getImmunities ( ).equals ( group.getImmunities ( ) )
                     && groupInList.getWeaknesses ( ).equals ( group.getWeaknesses ( ) )
                     && groupInList.getArmyType ( ) == group.getArmyType ( )
                     && groupInList.getNumberOfHitPoints ( ) == group.getNumberOfHitPoints ( ) )
                {
                    index = i;
                    break;
                }
            }

            final Group groupAdjusted = groups.get ( index );

            if ( groupAdjusted.getNumberOfUnits ( ) <= 0 )
            {
                continue;
            }

            final Integer entry = targets.get ( index );

            if ( entry == null )
            {
                continue;
            }

            final Group target = groups.get ( entry );

            final int damage = calculateDamage ( target, groupAdjusted.calcEffectivePower ( ),
                    groupAdjusted.getAttackType ( ) );

            final int unitsKilled = damage / target.getNumberOfHitPoints ( );

            if ( unitsKilled > 0 )
            {
                groups.set ( entry, Group.decreaseNumberOfUnits ( target, unitsKilled ) );
                killed = true;
            }
        }

        return killed;
    }

    private static Map< Integer, Integer > targetSelection ( final List< Group > groups )
    {
        Map< Integer, Integer > targets = new LinkedHashMap<> ( );

        final List< Group > sortedGroups = groups.stream ( )
                .sorted ( Comparator.reverseOrder ( ) )
                .collect ( Collectors.toList ( ) );

        for ( Group group : sortedGroups )
        {
            final int index = groups.indexOf ( group );

            final List< Group > opposition = getOpposition ( groups, group, targets );

            if ( !opposition.isEmpty ( ) )
            {
                final Group oppGroup = opposition.get ( 0 );
                targets.put ( index, groups.indexOf ( oppGroup ) );
            }
        }

        return targets;
    }

    private static List< Group > getOpposition (
            final List< Group > groups,
            final Group attackingGroup,
            final Map< Integer, Integer > targets )
    {
        return groups.stream ( )
                .filter ( e -> e.getArmyType ( ) == attackingGroup.getArmyType ( ).getOpposition ( ) )
                .filter ( e -> !e.getImmunities ( ).contains ( attackingGroup.getAttackType ( ) ) )
                .filter ( e -> !targets.containsValue ( groups.indexOf ( e ) ) )
                .sorted ( new OppositionComparator ( attackingGroup.calcEffectivePower ( ),
                        attackingGroup.getAttackType ( ) ) )
                .collect ( Collectors.toList ( ) );
    }

    private static final class OppositionComparator implements Comparator< Group >
    {
        private final int m_effectivePower;

        private final Weapon m_attackType;

        public OppositionComparator ( final int effectivePower, final Weapon attackType )
        {
            m_effectivePower = effectivePower;
            m_attackType = attackType;
        }

        @Override
        public int compare ( final Group o1, final Group o2 )
        {
            int res = Integer.compare (
                    m_effectivePower - calculateDamage ( o1, m_effectivePower, m_attackType ),
                    m_effectivePower - calculateDamage ( o2, m_effectivePower, m_attackType ) );

            if ( res != 0 )
            {
                return res;
            }

            res = Integer.compare ( o2.calcEffectivePower ( ), o1.calcEffectivePower ( ) );

            if ( res != 0 )
            {
                return res;
            }

            return Integer.compare ( o2.getInitiative ( ), o1.getInitiative ( ) );
        }
    }

    private static int calculateDamage ( final Group group, final int effectivePower, final Weapon attackType )
    {
        if ( group.getWeaknesses ( ).contains ( attackType ) )
        {
            return effectivePower * 2;
        }

        return effectivePower;
    }

    private static int numberOfArmies ( final List< Group > groups )
    {
        return ( int ) groups.stream ( ).map ( e -> e.getArmyType ( ) ).distinct ( ).count ( );
    }

    private static List< Group > parseInput ( final String input, final int boost )
    {
        final String[] lines = splitInput ( input );

        List< Group > groupList = Lists.newArrayList ( );

        ArmyType army = null;

        for ( String line : lines )
        {
            if ( line.isEmpty ( ) )
            {
                continue;
            }
            if ( line.contains ( ":" ) )
            {
                army = ArmyType.valueOf ( line.substring ( 0, line.length ( ) - 1 ).replaceAll ( " ", "" ) );
                continue;
            }

            final Matcher matcher = s_pattern.matcher ( line );

            if ( matcher.matches ( ) )
            {
                List< Weapon > weaknesses = Lists.newArrayList ( );
                List< Weapon > immunities = Lists.newArrayList ( );

                final String weakOrImmune = matcher.group ( 3 );

                if ( weakOrImmune != null )
                {
                    final String[] weaknessesOrImmunities = weakOrImmune.split ( ";" );

                    for ( String entry : weaknessesOrImmunities )
                    {
                        final String trimmedEntry = entry.replaceAll ( "\\(", "" )
                                .replaceAll ( "\\)", "" )
                                .trim ( );

                        if ( trimmedEntry.startsWith ( "weak to" ) )
                        {
                            weaknesses.addAll ( Arrays.stream ( trimmedEntry.substring ( 8 ).split ( ", " ) )
                                    .map ( e -> Weapon.valueOf ( StringUtils.capitalize ( e ) ) )
                                    .collect ( Collectors.toList ( ) ) );
                        }
                        else if ( trimmedEntry.startsWith ( "immune to" ) )
                        {
                            immunities.addAll ( Arrays.stream ( trimmedEntry.substring ( 10 ).split ( ", " ) )
                                    .map ( e -> Weapon.valueOf ( StringUtils.capitalize ( e ) ) )
                                    .collect ( Collectors.toList ( ) ) );
                        }
                    }
                }

                groupList.add ( new Group (
                        Integer.parseInt ( matcher.group ( 1 ) ),
                        Integer.parseInt ( matcher.group ( 2 ) ),
                        Integer.parseInt ( matcher.group ( 4 ) ) + ( army == ArmyType.ImmuneSystem ? boost : 0 ),
                        Weapon.valueOf ( StringUtils.capitalize ( matcher.group ( 5 ) ) ),
                        Integer.parseInt ( matcher.group ( 6 ) ),
                        weaknesses,
                        immunities,
                        army ) );

            }
        }

        return groupList;
    }

    private static final class Group implements Comparable< Group >
    {
        private final int m_numberOfUnits;

        private final int m_numberOfHitPoints;

        private final int m_attackDamage;

        private final Weapon m_attackType;

        private final int m_initiative;  //higher initiative units attack first and win ties

        private final List< Weapon > m_weaknesses;

        private final List< Weapon > m_immunities;

        private final ArmyType m_armyType;

        public Group (
                final int numberOfUnits,
                final int numberOfHitPoints,
                final int attackDamage,
                final Weapon attackType,
                final int initiative,
                final List< Weapon > weaknesses,
                final List< Weapon > immunities,
                final ArmyType armyType )
        {
            m_numberOfUnits = numberOfUnits;
            m_numberOfHitPoints = numberOfHitPoints;
            m_attackDamage = attackDamage;
            m_attackType = attackType;
            m_initiative = initiative;
            m_weaknesses = weaknesses;
            m_immunities = immunities;
            m_armyType = armyType;
        }

        public static Group decreaseNumberOfUnits ( final Group group, final int numberOfUnitsToDecrease )
        {
            return new Group (
                    group.getNumberOfUnits ( ) - numberOfUnitsToDecrease,
                    group.getNumberOfHitPoints ( ),
                    group.getAttackDamage ( ),
                    group.getAttackType ( ),
                    group.getInitiative ( ),
                    group.getWeaknesses ( ),
                    group.getImmunities ( ),
                    group.getArmyType ( ) );
        }

        public int getNumberOfUnits ( )
        {
            return m_numberOfUnits;
        }

        public int getNumberOfHitPoints ( )
        {
            return m_numberOfHitPoints;
        }

        public int getAttackDamage ( )
        {
            return m_attackDamage;
        }

        public Weapon getAttackType ( )
        {
            return m_attackType;
        }

        public int getInitiative ( )
        {
            return m_initiative;
        }

        public List< Weapon > getWeaknesses ( )
        {
            return m_weaknesses;
        }

        public List< Weapon > getImmunities ( )
        {
            return m_immunities;
        }

        public ArmyType getArmyType ( )
        {
            return m_armyType;
        }

        public int calcEffectivePower ( )
        {
            return m_numberOfUnits * m_attackDamage;
        }

        @Override
        public int compareTo ( @NotNull final Group o )
        {
            final int res = Integer.compare ( calcEffectivePower ( ), o.calcEffectivePower ( ) );
            if ( res != 0 )
            {
                return res;
            }
            return Integer.compare ( getInitiative ( ), o.getInitiative ( ) );
        }
    }

    enum Weapon
    {
        Cold,
        Fire,
        Slashing,
        Bludgeoning,
        Radiation
    }

    enum ArmyType
    {
        Infection,
        ImmuneSystem;

        public ArmyType getOpposition ( )
        {
            if ( this == Infection )
            {
                return ImmuneSystem;
            }
            if ( this == ImmuneSystem )
            {
                return Infection;
            }

            throw new AdventOfCodeException ( "Something has gone wrong...unknown opposition" );
        }
    }
}
