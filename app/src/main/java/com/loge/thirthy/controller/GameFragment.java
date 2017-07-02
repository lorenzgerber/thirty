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
import com.loge.thirthy.view.CombinationSpinnerChangeEvent;
import com.loge.thirthy.view.CombinationSpinnerChangeListener;
import com.loge.thirthy.view.DiceImageButtons;

import static com.loge.thirthy.controller.GameActivity.MODE_HIGHLIGHTED;
import static com.loge.thirthy.controller.GameActivity.MODE_SELECTED;

/**
 * Created by loge on 2017-06-22.
 */

public class GameFragment extends Fragment {

    private static int NUMBER_OF_ROUNDS = 10;
    private static int NUMBER_OF_DIE = 6;
    private static int DIFF_FACE_VALUE_TO_POINT_ARRAY_INDEX = 3;

    Game mGame;

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
        mGame = new Game();
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
                if(mDice.getMode() != MODE_HIGHLIGHTED){
                    Toast.makeText(getActivity(),
                            getResources().getText(R.string.choose_combination),
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                mGame.setPoints(
                        mCombinationSpinner.getCombinationItemId() -
                                DIFF_FACE_VALUE_TO_POINT_ARRAY_INDEX,
                        mDice.calculatePoints()
                );
                mCombinationSpinner.removeCurrentSpinnerItem();


                // Check Round
                if(mGame.getRound() < NUMBER_OF_ROUNDS){
                    mGame.resetThrow();
                    mThrowButton.setEnabled(true);
                    mDice.rollAllDice();
                    updateUI();
                } else {
                    mPointsTransferArray = mGame.getPointsArray().clone();
                    Intent intent = ResultActivity.newIntent(getActivity(), mPointsTransferArray);
                    startActivity(intent);
                    mGame.resetGame();
                    mCombinationSpinner.resetCombinationsList();
                    mDice.rollAllDice();
                    mDice.unselectAll();
                    updateUI();
                }
            }

        });

    }
}
