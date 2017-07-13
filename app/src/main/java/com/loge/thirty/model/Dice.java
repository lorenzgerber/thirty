/*
 * Dice
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
package com.loge.thirty.model;

import android.os.Parcel;
import android.os.Parcelable;

import static com.loge.thirty.controller.GameActivity.MODE_SHOW;
import static com.loge.thirty.controller.GameActivity.MODE_HIGHLIGHTED;
import static com.loge.thirty.controller.GameActivity.MODE_SELECTED;


/**
 * Dice
 *
 * Model Class that implements the game logic for
 * a set of die objects.
 */
public class Dice implements Parcelable {
    private final Die mDice[];
    private int mMode;


    /**
     * Dice
     *
     * Constructor for a set of die
     * objects.
     * @param size
     */
    public Dice(int size){
        mDice = new Die[size];
        for (int i = 0; i < size; i++){
            mDice[i] = new Die();
        }
    }


    /**
     * getMode
     *
     * Getter to obtain the mode of the dice. which could be
     * show, select (for rolling) or highlight (to visualize a point
     * combination)
     * @return
     */
    public int getMode(){
        return mMode;
    }

    /**
     * getDie
     *
     * Getter to access an individual die of the set.
     * @param index
     * @return
     */
    public Die getDie(int index){
        return mDice[index];
    }

    /**
     * setDie
     *
     * Setter for individual die in the set. Can be used
     * for testing in combination with custom constructor of
     * die that allows setting a specific value.
     * @param index
     * @param die
     */
    public void setDie(int index, Die die){
        mDice[index] = die;
    }

    /**
     * size()
     *
     * Returns the number of dice in the set
     * @return
     */
    public int size(){
        return mDice.length;
    }

    /**
     * getFaceValue
     *
     * Direct access to the value of a specific
     * die in the set.
     * @param index
     * @return
     */
    public int getFaceValue(int index){
        return mDice[index].getValue();
    }

    /**
     * rollAllDice
     *
     * Results in setting constructing
     * new die with random values.
     */
    public void rollAllDice(){
        for (int i = 0 ; i < mDice.length; i++){
            mDice[i] = new Die();
        }
        setMode(0);
    }

    /**
     * rollDie
     *
     * A specific Die in the set is replaced by
     * a new one with random value.
     * @param index
     */
    public void rollDie(int index){
        mDice[index] = new Die();
    }

    /**
     * setMode
     *
     * Set the mode for the whole dice set.
     * MODE_SHOW will reset all die to MODE_SHOW.
     * MODE_HIGHLIGHTED will reset all MODE_SELECTED
     * to MODE_SHOW and MODE_SELECTED vice versa.
     * @param mode
     */
    public void setMode(int mode){
        if(mode == MODE_SHOW){
            mMode = mode;
            for(Die die:mDice){
                die.setMode(MODE_SHOW);
            }
        } else if(mode == MODE_HIGHLIGHTED){
            mMode = mode;
            for(Die die:mDice){
                if(die.getMode() == 2){
                    die.setMode(0);
                }
            }
        } else if (mode == MODE_SELECTED){
            mMode = mode;
            for(Die die:mDice){
                if(die.getMode() == 1 ){
                    die.setMode(0);
                }
            }
        }
    }

    /**
     * calculatePoints
     *
     * Calculate the points from all
     * highlighted dice.
     * @return
     */
    public int calculatePoints(){
        int mPoints = 0;
        for (int i = 0; i < this.size(); i++){
            if (this.getDie(i).getMode()==MODE_HIGHLIGHTED){
                mPoints += this.getDie(i).getValue();
            }
        }
        return mPoints;
    }

    /**
     * describeContents
     *
     * Mandatory method for Parcelable
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * writeToParcel
     *
     * method that writes the array of dice and the
     * current mode to a parcel for state transfer
     * during configuration change.
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(mDice, 0);
        dest.writeInt(mMode);
    }

    /**
     * Dice
     *
     * private constructor to recreate state
     * after configuration change using parcels
     * for transient state storage.
     * @param in
     */
    private Dice(Parcel in){
        mDice = in.createTypedArray(Die.CREATOR);
    }

    /**
     * Parcelable.Creator
     *
     * static method that calls special constructor to
     * recreate state after configuration change.
     */
    public static final Parcelable.Creator<Dice> CREATOR = new Parcelable.Creator<Dice>(){

        @Override
        public Dice createFromParcel(Parcel source) {
            return new Dice(source);
        }

        @Override
        public Dice[] newArray(int size) {
            return new Dice[size];
        }
    };


}
