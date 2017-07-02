package com.loge.thirthy.controller;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.loge.thirthy.view.CombinationListItem;
import com.loge.thirthy.R;
import com.loge.thirthy.model.Dice;
import com.loge.thirthy.model.GameState;
import com.loge.thirthy.model.ValueChecker;
import com.loge.thirthy.view.CombinationSpinner;
import com.loge.thirthy.view.CombinationSpinnerChangeEvent;
import com.loge.thirthy.view.CombinationSpinnerChangeListener;
import com.loge.thirthy.view.DiceImageButtons;

import java.util.ArrayList;

/**
 * Created by loge on 2017-06-22.
 */

public class GameFragment extends Fragment {

    private static int NUMBER_OF_ROUNDS = 10;
    private static int NUMBER_OF_DIE = 6;

    GameState mGameState;

    DiceImageButtons mImageButtons;
    CombinationSpinner mCombinationSpinner;
    Button mThrowButton;
    Button mTakePointsButton;
    int mDieMode;
    Dice mDice;
    int[] mPointsTransferArray;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDice = new Dice(NUMBER_OF_DIE);
        mGameState = GameState.get(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        // Get ImageButton Widgets
        mImageButtons = new DiceImageButtons(v);
        mImageButtons.attachListeners(mDice);
        mImageButtons.updateImages(mDice);

        mCombinationSpinner = new CombinationSpinner(v, this, mDice);
        mCombinationSpinner.addCombinationSpinnerChangeListener(new CombinationSpinnerChangeListener(){

            @Override
            public void changeEventReceived(CombinationSpinnerChangeEvent evt) {
                updateUI();
            }
        });

        initThrowButton(v);
        initTakePointsButton(v);
        updateUI();

        return v;
    }

    private void updateUI() {

        mDieMode = mDice.getMode();
        mImageButtons.updateImages(mDice);
    }


    private void initThrowButton(View v){
        mThrowButton = (Button) v.findViewById(R.id.roll_dice);
        mThrowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                boolean mThrowAll = true;
                for (int i = 0; i < mDice.size(); i++){
                    if(mDice.getDie(i).getMode()==2){
                        mDice.rollDie(i);
                        mThrowAll = false;
                    }
                }
                if (mThrowAll){
                    mDice.rollAllDice();
                }

                if(mGameState.getThrow() == 1){
                    mThrowButton.setEnabled(false);
                } else {
                    mGameState.nextThrow();
                }

                mCombinationSpinner.setSpinnerPosition(0);
                updateUI();
            }
        });
    }

    private void initTakePointsButton(View v){
        mTakePointsButton = (Button) v.findViewById(R.id.take_points);
        mTakePointsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                // Check for mode 1 set, otherwise Toast
                if(mDice.getMode() != 1){
                    Toast.makeText(getActivity(),
                            getResources().getString(R.string.choose_combination),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate points
                int mPoints = 0;
                for (int i = 0; i < mDice.size(); i++){
                    if (mDice.getDie(i).getMode()==1){
                        mPoints += mDice.getDie(i).getValue();
                    }
                }

                mGameState.setPoints(mCombinationSpinner.getCombinationItemId()-3, mPoints);
                mCombinationSpinner.removeCurrentSpinnerItem();


                // Check Round
                if(mGameState.getRound() < NUMBER_OF_ROUNDS){
                    mGameState.resetThrow();
                    mThrowButton.setEnabled(true);
                    mDice.rollAllDice();
                    updateUI();
                } else {
                    mPointsTransferArray = mGameState.getPointsArray().clone();
                    Intent intent = ResultActivity.newIntent(getActivity(), mPointsTransferArray);
                    mGameState.resetGame();
                    mCombinationSpinner.resetCombinationsList();
                    mDice.rollAllDice();
                    mDice.unselectAll();
                    updateUI();
                    startActivity(intent);

                }
            }

        });

    }
}
