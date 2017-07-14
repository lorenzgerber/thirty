/*
 * SingleFragmentActivity
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
package com.loge.thirty.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.loge.thirty.R;

/**
 * Single FragmentActivity
 *
 * Abstract class used as mall
 * to create new specialized activities.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    /**
     * createFragment
     *
     * This method has to be overidden when implementing a concrete
     * activity class.
     * @return Fragment: returns new created fragment
     */
    protected abstract Fragment createFragment();

    /**
     * onCreate
     *
     * lifecycle method that contains generic code
     * to load the respective fragment using the
     * fragment manager and a transaction.
     * @param savedInstanceState Bundle: saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        Fragment fragment;

        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState != null){
            fragment = fm.getFragment(savedInstanceState, String.valueOf(R.id.fragment_container));
        } else {
            fragment = fm.findFragmentById(R.id.fragment_container);
        }

        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    /**
     * onSaveInstanceState
     *
     * Takes care of storing transient bundle data from
     * the fragment.
     * @param outState Bundle: Bundle in which to place saved state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        getSupportFragmentManager().putFragment(outState,String.valueOf(R.id.fragment_container), fragment);
    }

}
