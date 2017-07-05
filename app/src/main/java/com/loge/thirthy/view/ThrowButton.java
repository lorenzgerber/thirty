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
import com.loge.thirthy.model.Dice;
import com.loge.thirthy.model.Game;


import java.util.concurrent.CopyOnWriteArrayList;

import static com.loge.thirthy.controller.GameActivity.MODE_SELECTED;
import static com.loge.thirthy.controller.GameActivity.MODE_SHOW;

/**
 * Created by loge on 2017-07-05.
 */

public class ThrowButton {

    CombinationSpinner mCombinationSpinner;
    Game mGame;
    Dice mDice;
    Button mThrowButton;
    private final CopyOnWriteArrayList<ThrowButtonChangeListener> listeners;


    public ThrowButton(View v, Game game, Dice dice, CombinationSpinner spinner) {
        this.mCombinationSpinner = spinner;
        this.mGame = game;
        this.mDice = dice;
        this.listeners = new CopyOnWriteArrayList<>();


        mThrowButton = (Button) v.findViewById(R.id.roll_dice);
        mThrowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                boolean mThrowAll = true;
                for (int i = 0; i < mDice.size(); i++){
                    if(mDice.getDie(i).getMode()==MODE_SELECTED){
                        mDice.rollDie(i);
                        mThrowAll = false;
                    }
                }
                if (mThrowAll){
                    mDice.rollAllDice();
                }

                if(mGame.getThrow() == 1){
                    mThrowButton.setEnabled(false);
                } else {
                    mGame.nextThrow();
                }

                mDice.setMode(MODE_SHOW);
                mCombinationSpinner.setSpinnerPosition(0);
                fireChangeEvent();

            }
        });
    }

    public void setEnabled(Boolean enable){
        mThrowButton.setEnabled(enable);
    }

    public void addThrowButtonChangeListener(ThrowButtonChangeListener l){
        this.listeners.add(l);
    }


    protected void fireChangeEvent() {
        ThrowButtonChangeEvent evt = new ThrowButtonChangeEvent(this);
        for (ThrowButtonChangeListener l : listeners){
            l.changeEventReceived(evt);
        }
    }



}
