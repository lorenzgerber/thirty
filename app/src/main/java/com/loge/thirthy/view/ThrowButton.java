/*
 * ThrowButton
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

import android.view.View;
import android.widget.Button;

import com.loge.thirthy.R;


import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  ThrowButton Class
 *
 *  UI part for Button used to throw the dice.
 */
public class ThrowButton {

    Button mThrowButton;
    private final CopyOnWriteArrayList<ThrowButtonChangeListener> listeners;

    /**
     * Throw Button
     * Constructor
     * @param v
     */
    public ThrowButton(View v) {

        this.listeners = new CopyOnWriteArrayList<>();
        mThrowButton = (Button) v.findViewById(R.id.roll_dice);
        mThrowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                fireChangeEvent();

            }
        });
    }

    /**
     * setEnabled
     *
     * enable/disable the Dice Throw Button
     * @param enable
     */
    public void setEnabled(Boolean enable){
        mThrowButton.setEnabled(enable);
    }

    /**
     * addThrowButtonListener
     *
     * Register for updates on Throw Button click
     * @param l ThrowButtonChangeListener
     */
    public void addThrowButtonChangeListener(ThrowButtonChangeListener l){
        this.listeners.add(l);
    }

    /**
     * fireChangeEvent()
     *
     * Informs registered listeners on Throw Button clicked
     */
    protected void fireChangeEvent() {
        ThrowButtonChangeEvent evt = new ThrowButtonChangeEvent(this);
        for (ThrowButtonChangeListener l : listeners){
            l.changeEventReceived(evt);
        }
    }
}
