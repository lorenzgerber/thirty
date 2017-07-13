/*
 * DieTest
 *
 * Thirty Project,
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Summer Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 *
 */
package com.loge.thirty;

import com.loge.thirty.model.Die;

import org.junit.Test;

import static org.junit.Assert.*;


public class DieTest {

    @Test
    public void constructDieInRange() throws Exception {
        assertNotNull(new Die(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void dieValueToHigh() throws Exception {
        Die die = new Die(7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dieValueZero() throws Exception {
        Die die = new Die(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dieValueNegative() throws Exception {
        Die die = new Die(-5);
    }


}
