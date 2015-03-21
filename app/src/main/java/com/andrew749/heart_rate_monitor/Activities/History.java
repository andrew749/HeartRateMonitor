package com.andrew749.heart_rate_monitor.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.andrew749.heart_rate_monitor.R;

/**
 * Created by andrew on 3/17/15.
 * Class to display historical results which are saved in some database on the device.
 */
public class History extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historyview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
