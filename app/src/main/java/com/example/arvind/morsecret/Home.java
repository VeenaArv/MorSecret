package com.example.arvind.morsecret;
/**
 * @author Veena, Alice, Shannon
 * @created April 27, 2015
 *
 * Home Fragment displays on app.
 */

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class Home extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View in = inflater.inflate(R.layout.activity_home, container,false); // adds Home tab to main activity
        return in;
    }
}
