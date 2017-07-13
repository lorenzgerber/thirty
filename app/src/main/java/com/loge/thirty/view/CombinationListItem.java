/*
 * CombinationListItem
 *
 * Thirty Project, an Android implementation
 * of the Dice game 'thirty'.
 * Coursework 5DV155 Development of mobile applications
 * at Umea University, Summer Course 2017
 *
 * Lorenz Gerber
 *
 * Version 0.1
 *
 * Licensed under GPLv3
 */
package com.loge.thirty.view;

/**
 * CombinationListItem
 *
 * Is used in the UI Spinner to keep
 * bind text and id of the die combinations
 * together.
 */
class CombinationListItem {

    private final int mId;
    private final String mText;

    /**
     * CombinationListItem
     *
     * Constructor, assigns only the args
     * @param id sequence id of spinner list
     * @param text to be shown as list entry
     */
    public CombinationListItem(int id, String text){
        mId = id;
        mText = text;
    }

    /**
     * getId
     *
     * Getter for the id.
     * @return Id, the unique id of spinner entry
     */
    public int getId(){ return mId; }

    /**
     * toString
     *
     * In conjunction with the ArrayAdapter of
     * combination spinner, toString output is
     * used as the screen label.
     * @return string of the label text
     */
    @Override
    public String toString(){
        return mText;
    }
}
