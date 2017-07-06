/*
 * CombinationSpinner
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


import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.loge.thirthy.R;
import com.loge.thirthy.controller.GameFragment;
import com.loge.thirthy.model.Dice;
import com.loge.thirthy.model.Game;
import com.loge.thirthy.model.ValueChecker;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.loge.thirthy.controller.GameActivity.MODE_HIGHLIGHTED;
import static com.loge.thirthy.controller.GameActivity.MODE_SHOW;

/**
 * CombinationSpinner
 *
 * UI element for choosing the dice combination.
 * Uses ArrayAdapter to conveniently change the spinner
 * list items during runtime. Implements an observeable
 * pattern to inform back to caller when a selection
 * has been taken.
 */
public class CombinationSpinner implements AdapterView.OnItemSelectedListener {

    private static int NUMBER_OF_COMBINATIONS_SPINNER_ENTRIES = 11;
    private static int DIFF_SPINNER_INDEX_TO_FACE_VALUE = 2;

    private Spinner mSpinner;
    private int mSpinnerPosition;
    private Dice mDice;
    private Game mGame;
    private GameFragment mHostFragment;
    private ArrayAdapter<CombinationListItem> mAdapter;
    private ArrayList<CombinationListItem> mCombinationsLeft;
    private final CopyOnWriteArrayList<CombinationSpinnerChangeListener> listeners;

    /**
     * CombinationSpinner
     *
     * Constructor, initializes variables
     * and data.
     * @param v
     * @param f
     * @param dice
     * @param game
     */
    public CombinationSpinner(View v, GameFragment f, Dice dice, Game game){
        mDice = dice;
        mGame = game;
        mHostFragment = f;
        mCombinationsLeft = new ArrayList<>();
        resetCombinationsList();
        mSpinner = (Spinner) v.findViewById(R.id.choose_points);
        mAdapter = new ArrayAdapter<>(mHostFragment.getActivity(), android.R.layout.simple_spinner_dropdown_item, mCombinationsLeft);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(this);
        this.listeners = new CopyOnWriteArrayList<>();
        setSpinnerPosition(0);
    }

    /**
     * addCombinationSpinnerChangeListener
     *
     * Method to register for item selected events.
     * @param l
     */
    public void addCombinationSpinnerChangeListener(CombinationSpinnerChangeListener l){
        this.listeners.add(l);
    }


    /**
     * fireChangeEvent
     *
     * Informs all registered listeners about
     * item selected event
     */
    protected void fireChangeEvent() {
        CombinationSpinnerChangeEvent evt = new CombinationSpinnerChangeEvent(this);
        for (CombinationSpinnerChangeListener l : listeners){
            l.changeEventReceived(evt);
        }
    }


    /**
     * onItemSelected
     *
     * Built in item selection event. Used
     * to adapt the UI data structures that control
     * which items should be shown in the spinner
     * @param parent used for posting toasts
     * @param view the view in which the spinner is rendered
     * @param position int spinner position
     * @param id not used in the current implementation
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0){
            ValueChecker mValueChecker = new ValueChecker(mDice);
            boolean[] mCombination = mValueChecker.getCombination(mCombinationsLeft.get(position).getId());
            mDice.setMode(MODE_SHOW);
            for(int i = 0; i < 6; i++){
                if (mCombination[i]){
                    mDice.getDie(i).setMode(MODE_HIGHLIGHTED);
                }
            }
            mDice.setMode(MODE_HIGHLIGHTED);
            mSpinnerPosition = position;
            fireChangeEvent();
            Toast.makeText(mHostFragment.getActivity(), String.valueOf(
                    mValueChecker.getPoints(mCombinationsLeft.get(mSpinnerPosition).getId())) +
                    " points!", Toast.LENGTH_SHORT).show();
        } else {
            mDice.setMode(MODE_SHOW);
            mSpinnerPosition = position;
            fireChangeEvent();
        }
    }

    /**
     * onNothingSelected
     *
     * Mandatory method. Not used in the current
     * implementation.
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSpinnerPosition = 0;
    }

    /**
     * resetCombinationsList
     *
     * resetting all data structures related to UI
     * of the spinner combinations list.
     */
    public void resetCombinationsList(){
        Resources res = mHostFragment.getResources();
        String[] mCombinationsText = res.getStringArray(R.array.combination_list);
        mCombinationsLeft.clear();

        for (int i = 0; i < NUMBER_OF_COMBINATIONS_SPINNER_ENTRIES; i++ ) {
            if(i > 0){
                if(!mGame.roundComplete(i-1))
                mCombinationsLeft.add(
                        new CombinationListItem(i + DIFF_SPINNER_INDEX_TO_FACE_VALUE,
                                mCombinationsText[i])
                );

            } else {
                mCombinationsLeft.add(
                        new CombinationListItem(i + DIFF_SPINNER_INDEX_TO_FACE_VALUE,
                                mCombinationsText[i])
                );
            }
        }
    }

    /**
     * setSpinnerPosition
     *
     * Programatic selection of a spinner
     * position.
     *
     * @param index
     */
    public void setSpinnerPosition(int index) {
        mSpinner.setSelection(index);
    }

    /**
     * getCombinationItemId
     *
     * Getter for the id of the combinationItem in
     * the ArrayAdapter
     * @return
     */
    public int getCombinationItemId(){
        return mCombinationsLeft.get(mSpinnerPosition).getId();
    }

    /**
     * removeCurrentSpinnerItem
     *
     * removes the CombinationListItem from the
     * array adapter
     */
    public void removeCurrentSpinnerItem(){
        mCombinationsLeft.remove(mSpinnerPosition);
        this.setSpinnerPosition(0);
    }

}
