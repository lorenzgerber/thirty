/*
 * CombinationListItem
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
 * Created by loge on 2017-06-23.
 */

public class CombinationListItem {

    private int mId;
    private String mText;

    public CombinationListItem(int id, String text){
        mId = id;
        mText = text;
    }

    public int getId(){ return mId; }

    @Override
    public String toString(){
        return mText;
    }
}
