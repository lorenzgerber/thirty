package com.loge.thirthy;

import com.loge.thirthy.model.Dice;
import com.loge.thirthy.model.Die;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by loge on 2017-06-16.
 */
public class DiceTest {

    @Test
    public void constructEmptyDice() throws Exception {
        assertNotNull(new Dice.Builder(0).build());
    }

    @Test
    public void constrcutDiceOneDie() throws Exception {
        assertNotNull(new Dice.Builder(1).addDie(new Die(1)).build());
    }

    @Test
    public void constructDiceTwoDie() throws Exception {
        assertNotNull(new Dice.Builder(2).addDie(new Die(1)).addDie(new Die(1)).build());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addNoDices() throws Exception {
        new Dice.Builder(1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTooManyDie() throws Exception {
        new Dice.Builder(1).addDie(new Die(1)).addDie(new Die(1)).build();
    }


}