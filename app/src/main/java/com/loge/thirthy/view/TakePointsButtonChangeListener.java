/*
 * TakePointsButtonChangeListener
 *
 * Thirty Project, an Android implementation
 * of the Dice game 'thirty'.
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Sumemr Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 */
package com.loge.thirthy.view;

/**
 * TakePointsButtonChangeListener
 *
 * Interface to be implemented when updates
 * on pressing the TakePoints Button are
 * needed.
 */
public interface TakePointsButtonChangeListener {
    public void changeEventReceived(TakePointsButtonChangeEvent ev);
}
