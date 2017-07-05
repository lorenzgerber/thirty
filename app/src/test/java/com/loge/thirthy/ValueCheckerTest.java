/*
 * ValueCheckerTest
 *
 * Thirty Project,
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Sumemr Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 *
 */
package com.loge.thirthy;

import com.loge.thirthy.model.Dice;
import com.loge.thirthy.model.Die;
import com.loge.thirthy.model.ValueChecker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by loge on 2017-06-17.
 */
public class ValueCheckerTest {

    @Test
    public void bitmaskForFourCombintations() throws Exception {
        Dice dice = new Dice(6);
        dice.setDie(0, new Die(1));
        dice.setDie(1, new Die(2));
        dice.setDie(2, new Die(3));
        dice.setDie(3, new Die(4));
        dice.setDie(4, new Die(5));
        dice.setDie(5, new Die(6));


        ValueChecker vc = new ValueChecker(dice);

        boolean bitmaskThree[] = {true, true, true, false, false, false};
        boolean bitmaskFour[] = {true, false, true, true, false, false};
        boolean bitmaskFive[] = {true, true, true, true, true, false};
        boolean bitmaskSix[]= {true, true, false, true, true, true};
        boolean bitmaskSeven[]= {true, true, true, true, true, true, };
        boolean bitmaskEight[]= {false, true, true, false, true, true};
        boolean bitmaskNine[]= {false, false, true, true, true, true};
        boolean bitmaskTen[]= {false, true, true, true, true, true};
        boolean bitmaskEleven[]= {false, false, false, false, true, true};
        boolean bitmaskTvelve[]= {false, false, true, true, true, false};


        assertArrayEquals(bitmaskThree, vc.getCombination(3));
        assertArrayEquals(bitmaskFour, vc.getCombination(4));
        assertArrayEquals(bitmaskFive, vc.getCombination(5));
        assertArrayEquals(bitmaskSix, vc.getCombination(6));
        assertArrayEquals(bitmaskSeven, vc.getCombination(7));
        assertArrayEquals(bitmaskEight, vc.getCombination(8));
        assertArrayEquals(bitmaskNine, vc.getCombination(9));
        assertArrayEquals(bitmaskTen, vc.getCombination(10));
        assertArrayEquals(bitmaskEleven, vc.getCombination(11));
        assertArrayEquals(bitmaskTvelve, vc.getCombination(12));

    }

    @Test
    public void testRun() throws Exception {
        Dice dice = new Dice(6);
        dice.setDie(0, new Die(1));
        dice.setDie(1, new Die(2));
        dice.setDie(2, new Die(3));
        dice.setDie(3, new Die(4));
        dice.setDie(4, new Die(5));
        dice.setDie(5, new Die(6));

        ValueChecker vc = new ValueChecker(dice);
        vc.getCombination(10);
    }

    @Test
    public void calcPointsLow() throws Exception {
        Dice dice = new Dice(6);
        dice.setDie(0, new Die(1));
        dice.setDie(1, new Die(2));
        dice.setDie(2, new Die(3));
        dice.setDie(3, new Die(4));
        dice.setDie(4, new Die(5));
        dice.setDie(5, new Die(6));
        ValueChecker vc = new ValueChecker(dice);
        assert(vc.getPoints(3) == 6 );
    }

    @Test
    public void calcPointsLowZero() throws Exception {
        Dice dice = new Dice(6);
        dice.setDie(0, new Die(4));
        dice.setDie(1, new Die(4));
        dice.setDie(2, new Die(4));
        dice.setDie(3, new Die(4));
        dice.setDie(4, new Die(5));
        dice.setDie(5, new Die(6));
        ValueChecker vc = new ValueChecker(dice);
        assert(vc.getPoints(3) == 0 );
    }

    @Test
    public void calcPointsAllSame() throws Exception {
        Dice dice = new Dice(6);
        dice.setDie(0, new Die(4));
        dice.setDie(1, new Die(4));
        dice.setDie(2, new Die(4));
        dice.setDie(3, new Die(4));
        dice.setDie(4, new Die(4));
        dice.setDie(5, new Die(4));
        ValueChecker vc = new ValueChecker(dice);
        assert(vc.getPoints(4) == 24 );

    }


}