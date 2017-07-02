package com.loge.thirthy.view;


import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.loge.thirthy.R;
import com.loge.thirthy.controller.GameFragment;
import com.loge.thirthy.model.Dice;
import com.loge.thirthy.model.ValueChecker;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by lgerber on 7/1/17.
 */

public class CombinationSpinner implements AdapterView.OnItemSelectedListener {

    private static int NUMBER_OF_COMBINATIONS_SPINNER_ENTRIES = 11;
    private static int DIFF_LIST_INDEX_TO_FACE_VALUE = 2;

    private Spinner mSpinner;
    private int mSpinnerPosition;
    private Dice mDice;
    private GameFragment mHostFragment;
    private ArrayAdapter<CombinationListItem> mAdapter;
    private ArrayList<CombinationListItem> mCombinationsLeft;
    private final CopyOnWriteArrayList<CombinationSpinnerChangeListener> listeners;

    public CombinationSpinner(View v, GameFragment f, Dice dice){
        mDice = dice;
        mHostFragment = f;
        mCombinationsLeft = new ArrayList<>();
        resetCombinationsList();



        mSpinner = (Spinner) v.findViewById(R.id.choose_points);
        mAdapter = new ArrayAdapter<>(mHostFragment.getActivity(), android.R.layout.simple_spinner_dropdown_item, mCombinationsLeft);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(this);
        this.listeners = new CopyOnWriteArrayList<>();
    }

    public void addCombinationSpinnerChangeListener(CombinationSpinnerChangeListener l){
        this.listeners.add(l);
    }
    

    protected void fireChangeEvent() {
        CombinationSpinnerChangeEvent evt = new CombinationSpinnerChangeEvent(this);
        for (CombinationSpinnerChangeListener l : listeners){
            l.changeEventReceived(evt);
        }
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
            fireChangeEvent();
            Toast.makeText(mHostFragment.getActivity(), String.valueOf(
                    mValueChecker.getPoints(mCombinationsLeft.get(mSpinnerPosition).getId())) +
                    " points!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mSpinnerPosition = 0;

    }

    public void resetCombinationsList(){
        Resources res = mHostFragment.getResources();
        String[] mCombinationsText = res.getStringArray(R.array.combination_list);
        mCombinationsLeft.clear();
        for (int i = 0; i < NUMBER_OF_COMBINATIONS_SPINNER_ENTRIES; i++ ) {
            mCombinationsLeft.add(
                    new CombinationListItem(i + DIFF_LIST_INDEX_TO_FACE_VALUE,
                    mCombinationsText[i])
            );
        }
    }

    public void setSpinnerPosition(int index) {
        mSpinner.setSelection(index);
    }

    public int getCombinationItemId(){
        return mCombinationsLeft.get(mSpinnerPosition).getId();
    }

    public void removeCurrentSpinnerItem(){
        mCombinationsLeft.remove(mSpinnerPosition);
        this.setSpinnerPosition(0);
    }
}
