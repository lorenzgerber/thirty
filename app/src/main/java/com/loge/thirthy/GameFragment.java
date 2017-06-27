package com.loge.thirthy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by loge on 2017-06-22.
 */

public class GameFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    DiceState mDiceState;
    GameState mGameState;
    ArrayList<ImageButton> mImageButtons = new ArrayList<>();
    Button mThrowButton;
    Button mTakePointsButton;
    ArrayList<CombinationListItem> mCombinationsLeft;
    Spinner mSpinner;
    int[][] mImageIds;
    int mDieMode;
    int mCombinationChosen;
    int mSpinnerPosition;
    Dice mDice;
    ArrayAdapter<CombinationListItem> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mDiceState = DiceState.get(getActivity());
        mGameState = GameState.get(getActivity());
        mCombinationsLeft = mDiceState.getCombinationsLeft();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        // Get ImageButton Widgets
        initImageButtons(v);
        initThrowButton(v);
        initTakePointsButton(v);
        initSpinner(v);
        attachImageButtonListeners();
        mImageIds = new int[3][6];
        initImageIds();
        updateUI();

        return v;
    }

    private void updateUI() {


        mDice = mDiceState.getDice();
        mDieMode = mDice.getMode();

        for (Die die : mDice){
            mImageButtons.get(die.getPosition())
                    .setImageResource(mImageIds[die.getMode()][die.getValue()-1]);
        }
    }

    private void initImageButtons(View v){
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_one));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_two));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_three));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_four));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_five));
        mImageButtons.add((ImageButton) v.findViewById(R.id.die_six));
    }

    private void attachImageButtonListeners(){
        mImageButtons.get(0).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(0).getMode() > 0 ){
                    mDice.getDie(0).setMode(0);
                } else {
                    mDice.getDie(0).setMode(2);
                }
                updateUI();
            }
        });

        mImageButtons.get(1).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(1).getMode() > 0 ){
                    mDice.getDie(1).setMode(0);
                } else {
                    mDice.getDie(1).setMode(2);
                }
                updateUI();
            }
        });
        mImageButtons.get(2).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(2).getMode() > 0 ){
                    mDice.getDie(2).setMode(0);
                } else {
                    mDice.getDie(2).setMode(2);
                }
                updateUI();
            }
        });
        mImageButtons.get(3).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(3).getMode() > 0 ){
                    mDice.getDie(3).setMode(0);
                } else {
                    mDice.getDie(3).setMode(2);
                }
                updateUI();
            }
        });
        mImageButtons.get(4).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(4).getMode() > 0 ){
                    mDice.getDie(4).setMode(0);
                } else {
                    mDice.getDie(4).setMode(2);
                }
                updateUI();
            }
        });
        mImageButtons.get(5).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDice.setMode(2);
                if(mDice.getDie(5).getMode() > 0 ){
                    mDice.getDie(5).setMode(0);
                } else {
                    mDice.getDie(5).setMode(2);
                }
                updateUI();
            }
        });
    }

    private void initThrowButton(View v){
        mThrowButton = (Button) v.findViewById(R.id.roll_dice);
        mThrowButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                boolean mThrowAll = true;
                for (Die die: mDice){
                    if(die.getMode() == 2){
                        mDiceState.rollDice(die.getPosition());
                        mThrowAll = false;
                    }
                }
                if (mThrowAll){
                    mDiceState.rollAllDice();
                    mDice = mDiceState.getDice();
                }

                if(mGameState.getThrow() == 3){
                    mThrowButton.setEnabled(false);
                } else {
                    mGameState.nextThrow();
                }

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

                // Calculate points
                int mPoints = 0;
                for(Die die:mDice){
                    if(die.getMode()==1){
                        mPoints += die.getValue();
                    }
                }
                //mGameState.setPoints();
                // remove combination from list
                mCombinationsLeft.remove(mSpinnerPosition);
                mAdapter.notifyDataSetChanged();
                mSpinner.setAdapter(mAdapter);

                // Check Round
                // throw all dice
            }

        });

    }

    private void initSpinner(View v){
        mSpinner = (Spinner) v.findViewById(R.id.choose_points);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mCombinationsLeft);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    private void initImageIds(){
        mImageIds[0][0] = R.drawable.white1;
        mImageIds[0][1] = R.drawable.white2;
        mImageIds[0][2] = R.drawable.white3;
        mImageIds[0][3] = R.drawable.white4;
        mImageIds[0][4] = R.drawable.white5;
        mImageIds[0][5] = R.drawable.white6;
        mImageIds[1][0] = R.drawable.grey1;
        mImageIds[1][1] = R.drawable.grey2;
        mImageIds[1][2] = R.drawable.grey3;
        mImageIds[1][3] = R.drawable.grey4;
        mImageIds[1][4] = R.drawable.grey5;
        mImageIds[1][5] = R.drawable.grey6;
        mImageIds[2][0] = R.drawable.red1;
        mImageIds[2][1] = R.drawable.red2;
        mImageIds[2][2] = R.drawable.red3;
        mImageIds[2][3] = R.drawable.red4;
        mImageIds[2][4] = R.drawable.red5;
        mImageIds[2][5] = R.drawable.red6;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0){
            ValueChecker mValueChecker = new ValueChecker(mDiceState.getDice());
            boolean[] mCombination = mValueChecker.getCombination(mCombinationsLeft.get(position).getId());
            mDice.setMode(2);
            for(int i = 0; i < 6; i++){
                if (mCombination[i]){
                    mDice.getDie(i).setMode(1);
                }
            }
            // get id from combination and set as active
            CombinationListItem mSelectedObject = (CombinationListItem) mSpinner.getAdapter().getItem(position);
            int mCombinationChosen = mSelectedObject.getId();
            mSpinnerPosition = position;
            updateUI();
            Toast.makeText(getActivity(), String.valueOf(mValueChecker.getPoints(position + 2)) + " points!", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
