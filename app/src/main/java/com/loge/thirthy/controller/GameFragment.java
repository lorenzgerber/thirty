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
import com.loge.thirthy.view.DiceImageButtons;

import java.util.ArrayList;

/**
 * Created by loge on 2017-06-22.
 */

public class GameFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private static int NUMBER_OF_ROUNDS = 10;
    private static int NUMBER_OF_COMBINATIONS_SPINNER_ENTRIES = 11;

    GameState mGameState;

    DiceImageButtons mImageButtons;
    Button mThrowButton;
    Button mTakePointsButton;
    ArrayList<CombinationListItem> mCombinationsLeft;
    Spinner mSpinner;
    int mDieMode;
    int mSpinnerPosition;
    Dice mDice;
    ArrayAdapter<CombinationListItem> mAdapter;
    int[] mPointsTransferArray;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDice = new Dice(6);
        mGameState = GameState.get(getActivity());
        buildCombinationsList();
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


        initThrowButton(v);
        initTakePointsButton(v);
        initSpinner(v);
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

                if(mGameState.getThrow() == 2){
                    mThrowButton.setEnabled(false);
                } else {
                    mGameState.nextThrow();
                }

                mSpinner.setSelection(0);
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

                // set points in the array
                mGameState.setPoints(mCombinationsLeft.get(mSpinnerPosition).getId()-3, mPoints);

                // remove combination from list
                mCombinationsLeft.remove(mSpinnerPosition);
                mAdapter.notifyDataSetChanged();
                mSpinner.setAdapter(mAdapter);

                // Check Round
                if(mGameState.getRound() < NUMBER_OF_ROUNDS){
                    mGameState.nextRound();
                    mGameState.resetThrow();
                    mThrowButton.setEnabled(true);
                    mDice.rollAllDice();
                    updateUI();
                } else {
                    mPointsTransferArray = mGameState.getPointsArray().clone();
                    Intent intent = ResultActivity.newIntent(getActivity(), mPointsTransferArray);
                    mGameState.resetGame();
                    resetCombinationsList();
                    mDice.rollAllDice();
                    mDice.unselectAll();
                    updateUI();
                    startActivity(intent);

                }
            }

        });

    }

    private void initSpinner(View v){
        mSpinner = (Spinner) v.findViewById(R.id.choose_points);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mCombinationsLeft);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0){
            ValueChecker mValueChecker = new ValueChecker(mDice);
            boolean[] mCombination = mValueChecker.getCombination(mCombinationsLeft.get(position).getId());
            mDice.setMode(0);
            for(int i = 0; i < 6; i++){
                if (mCombination[i]){
                    mDice.getDie(i).setMode(1);
                }
            }
            mDice.setMode(1);
            mSpinnerPosition = position;
            updateUI();
            Toast.makeText(getActivity(), String.valueOf(
                    mValueChecker.getPoints(mCombinationsLeft.get(mSpinnerPosition).getId())) +
                    " points!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void buildCombinationsList(){
        Resources res = getResources();
        String[] mCombinationsText = res.getStringArray(R.array.combination_list);
        mCombinationsLeft = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_COMBINATIONS_SPINNER_ENTRIES; i++ ) {
            mCombinationsLeft.add(new CombinationListItem(i + 2, mCombinationsText[i]) );
        }
    }

    public void resetCombinationsList(){
        Resources res = getResources();
        String[] mCombinationsText = res.getStringArray(R.array.combination_list);

        for (int i = 0; i < NUMBER_OF_COMBINATIONS_SPINNER_ENTRIES-1; i++ ) {
            mCombinationsLeft.add(new CombinationListItem(i + 2, mCombinationsText[i]) );
        }
    }


}
