/*
 * ThrowButtonChangeEventListener
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
 * ThrowButtonChangeListener
 *
 * Interface to be implemented when updates
 * on pressing the ThrowButton are
 * needed.
 */
public interface ThrowButtonChangeListener {
    public void changeEventReceived(ThrowButtonChangeEvent ev);
}
