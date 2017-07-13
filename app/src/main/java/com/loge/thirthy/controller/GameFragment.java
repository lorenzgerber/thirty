/*
 * GameFragment
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
package com.loge.thirthy.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.loge.thirthy.R;
import com.loge.thirthy.model.Dice;
import com.loge.thirthy.model.Game;
import com.loge.thirthy.view.CombinationSpinner;
import com.loge.thirthy.view.CombinationSpinnerChangeListener;
import com.loge.thirthy.view.DiceImageButtons;

import static com.loge.thirthy.controller.GameActivity.MODE_HIGHLIGHTED;
import static com.loge.thirthy.controller.GameActivity.MODE_SELECTED;
import static com.loge.thirthy.controller.GameActivity.MODE_SHOW;

/**
 * GameFragment
 *
 * Fragment that serves as main controller for the
 * game. The fragment handles re-establishing states
 * on configuration changes using extras and parcelable objects.
 */
public class GameFragment extends Fragment {

    private static final String GAME_PARCEL = "com.loge.thirty.game";
    private static final String DICE_PARCEL = "com.loge.thirty.dice";
    private static final String DIE_MODE = "com.loge.thirty.dieMode";

    private Game mGame;
    private int mDieMode;
    private Dice mDice;

    private DiceImageButtons mImageButtons;
    private CombinationSpinner mCombinationSpinner;
    private Button mThrowButton;
    private Button mTakePointsButton;


    /**
     * onCreate
     *
     * Handles restoring state by receiving parcelable
     * data and extras when available.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            mGame = savedInstanceState.getParcelable(GAME_PARCEL);
            mDice = savedInstanceState.getParcelable(DICE_PARCEL);
            mDieMode = savedInstanceState.getInt(DIE_MODE);
        } else {
            int NUMBER_OF_DIE = 6;
            mDice = new Dice(NUMBER_OF_DIE);
            mGame = new Game();
        }
    }

    /**
     *
     * onCreateView
     *
     * Sets up the UI for rendering. Constructs all
     * UI artefacts such as ImageButtons, CombiationSpinner,
     * ThrowButton and TakePointsButton.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        mImageButtons = new DiceImageButtons(v);
        mImageButtons.attachListeners(mDice);
        mImageButtons.updateImages(mDice);

        mCombinationSpinner = new CombinationSpinner(v, this, mDice, mGame);
        mCombinationSpinner.addCombinationSpinnerChangeListener(new CombinationSpinnerChangeListener(){

            @Override
            public void changeEventReceived() {
                updateUI();
            }
        });

        mThrowButton = (Button) v.findViewById(R.id.roll_dice);
        mThrowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                throwDice();
            }
        });

        /*
        TakePointsButton takePointsButton = new TakePointsButton(v);
        takePointsButton.addTakePointsButtonChangeListener(new TakePointsButtonChangeListener() {
            @Override
            public void changeEventReceived() {
                checkRound();
            }
        });
        */
        mTakePointsButton = (Button) v.findViewById(R.id.take_points);
        mTakePointsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                checkRound();
            }
        });


        if(mGame.getThrow() == 1){
            mThrowButton.setEnabled(false);
        }
        updateUI();

        return v;
    }

    /**
     * onSaveInstanceState
     *
     * mGame, mDice and the mDieMode are prepared
     * here for transient storage in a bundle on
     * configuration change such as rotation.
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelable(GAME_PARCEL, mGame);
        outState.putParcelable(DICE_PARCEL, mDice);
        outState.putInt(DIE_MODE, mDieMode);
    }

    /**
     * throwDice
     *
     * Controller method that checks UI conditions
     * and eventually initiates rolling the dice.
     * Finally, updateUI is called.
     */
    private void throwDice(){
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
        mCombinationSpinner.setSpinnerPosition();
        updateUI();
    }

    /**
     * checkRound
     *
     * Controller method that checks UI and game
     * conditions whether the game can advance to
     * the next round and account for the points.
     * Finally the updateUI method is called.
     */
    private void checkRound(){

        if(mDice.getMode() != MODE_HIGHLIGHTED){
            Toast.makeText(getActivity(),
                    getResources().getText(R.string.choose_combination),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int DIFF_FACE_VALUE_TO_POINT_ARRAY_INDEX = 3;
        mGame.setPoints(
                mCombinationSpinner.getCombinationItemId() -
                        DIFF_FACE_VALUE_TO_POINT_ARRAY_INDEX,
                mDice.calculatePoints()
        );
        mCombinationSpinner.removeCurrentSpinnerItem();

        int NUMBER_OF_ROUNDS = 10;
        if(mGame.getRound() < NUMBER_OF_ROUNDS){
            mGame.resetThrow();
            mThrowButton.setEnabled(true);
            mDice.rollAllDice();
            updateUI();
        } else {
            int[] pointsTransferArray = mGame.getPointsArray().clone();
            Intent intent = ResultActivity.newIntent(getActivity(), pointsTransferArray);
            startActivity(intent);
            mGame.resetGame();
            mCombinationSpinner.resetCombinationsList();
            mDice.rollAllDice();
            mDice.setMode(0);
            updateUI();
        }
    }

    /**
     * updateUI
     *
     * Method that takes care of updating the dice
     * mode with the correct images before the
     * UI is redrawn.
     */
    private void updateUI() {

        mDieMode = mDice.getMode();
        mImageButtons.updateImages(mDice);
    }
}
