package com.example.arvind.morsecret;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 *@created Veena Arvind Alice Bian Shannon Chen,
 *
 * PagerAdapter: Represents each page (Home, Manual, and Translate) as a fragment,
 *               and situates them as tabs.
 *
 */
public class MorSecret_PagerAdapter extends FragmentPagerAdapter {
    public MorSecret_PagerAdapter(FragmentManager fm) {
        super(fm);
    }

        @Override
        // number of tabs
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            // first tab is the home tab. Displays that fragment
            if (position == 0) {
                return new Home();
            }
            // next tab is the Manual tab. Displays that fragment
            if(position==1) {
                return new Manual();
            }
            // last tab is Translate tab. Displays that fragment
            else{
                return new Translate();
            }

        }
    }

