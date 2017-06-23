package com.loge.thirthy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by loge on 2017-06-22.
 */

public class GameFragment extends Fragment {

    GameState mGameState;
    ArrayList<ImageButton> mImageButtons = new ArrayList<>();
    int[][] mImageIds;
    int mDieMode;
    Dice mDice;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mGameState = GameState.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        // Get ImageButton Widgets
        initImageButtons(v);
        attachListeners();
        mImageIds = new int[3][6];
        initImageIds();
        updateUI();

        return v;
    }



    private void updateUI() {


        mDice = mGameState.getDice();
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

    private void attachListeners(){
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

}
