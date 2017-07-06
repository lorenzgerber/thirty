/*
 * CombinationSpinnerChangeEvent
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

import java.util.EventObject;

/**
 * CombinationSpinnerChangeEvent
 *
 * Used to signal that an item has been selected
 * in the combination spinner
 */
public class CombinationSpinnerChangeEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CombinationSpinnerChangeEvent(Object source) {
        super(source);
    }
}
