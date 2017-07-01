package com.loge.thirthy;

import com.loge.thirthy.model.Die;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by loge on 2017-06-16.
 */
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
