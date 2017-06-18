package com.loge.thirthy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by loge on 2017-06-17.
 */
public class ValueCheckerTest {

    @Test
    public void bitmaskForFourCombintations() throws Exception {
        Dice dice = new Dice.Builder(6)
                .addDie(new Die(1))
                .addDie(new Die(2))
                .addDie(new Die(3))
                .addDie(new Die(4))
                .addDie(new Die(5))
                .addDie(new Die(6))
                .build();

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
        boolean bitmaskTvelve[]= {true, false, false, false, true, true};


        assertArrayEquals(bitmaskThree, vc.checkFor(3));
        assertArrayEquals(bitmaskFour, vc.checkFor(4));
        assertArrayEquals(bitmaskFive, vc.checkFor(5));
        assertArrayEquals(bitmaskSix, vc.checkFor(6));
        assertArrayEquals(bitmaskSeven, vc.checkFor(7));
        assertArrayEquals(bitmaskEight, vc.checkFor(8));
        assertArrayEquals(bitmaskNine, vc.checkFor(9));
        assertArrayEquals(bitmaskTen, vc.checkFor(10));
        assertArrayEquals(bitmaskEleven, vc.checkFor(11));
        assertArrayEquals(bitmaskTvelve, vc.checkFor(12));

    }

    @Test
    public void testRun() throws Exception {
        Dice dice = new Dice.Builder(6)
                .addDie(new Die(1))
                .addDie(new Die(2))
                .addDie(new Die(3))
                .addDie(new Die(4))
                .addDie(new Die(5))
                .addDie(new Die(6))
                .build();

        ValueChecker vc = new ValueChecker(dice);
        vc.checkFor(10);


    }


}