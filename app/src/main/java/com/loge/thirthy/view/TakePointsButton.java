/*
 * TakePointsButton
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
 * Created by loge on 2017-07-05.
 */

public class TakePointsButton {

    Button mTakePointsButton;
    private final CopyOnWriteArrayList<TakePointsButtonChangeListener> listeners;

    public TakePointsButton(View v){

        this.listeners = new CopyOnWriteArrayList<>();

        mTakePointsButton = (Button) v.findViewById(R.id.take_points);
        mTakePointsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                fireChangeEvent();
            }
        });


    }

    public void addTakePointsButtonChangeListener(TakePointsButtonChangeListener l){
        this.listeners.add(l);
    }


    protected void fireChangeEvent() {
        TakePointsButtonChangeEvent evt = new TakePointsButtonChangeEvent(this);
        for (TakePointsButtonChangeListener l : listeners){
            l.changeEventReceived(evt);
        }
    }



}
