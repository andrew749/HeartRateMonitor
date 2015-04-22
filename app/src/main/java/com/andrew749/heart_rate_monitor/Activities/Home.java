package com.andrew749.heart_rate_monitor.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.andrew749.heart_rate_monitor.R;
import com.andrew749.tutorialcardlibrary.TutorialEntry;
import com.andrew749.tutorialcardlibrary.TutorialsCardActivity;

import java.util.ArrayList;

/**
 * Created by andrew on 2/8/15.
 */
public class Home extends Activity implements View.OnClickListener {
    //TODO run tutorial livrary to show user how to operate monitor
    Button quickMeasurement;
    final static String PREFS_NAME = "HeartRateMonitorSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //check to see if first time launching and launch tutorial
//        TutorialsCardActivity activity = new TutorialsCardActivity();
        quickMeasurement = (Button) findViewById(R.id.quickMeasurementButton);
        quickMeasurement.setOnClickListener(this);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            String lastVersion =settings.getString("versionname","0");

            if (!lastVersion.equalsIgnoreCase(versionName)) {
                //the app is being launched for first time, do something
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setTitle(getString(R.string.first_time_dialog_title));
                dialogBuilder.setMessage(getString(R.string.first_time_dialog_message));
                dialogBuilder.setCancelable(false);
                dialogBuilder.setPositiveButton(getString(R.string.yesbutton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //run video tutorial
                        runTutorial();
                    }
                });
                dialogBuilder.setNegativeButton(getString(R.string.nobutton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();
                // first time task
                // record the fact that the app has been started at least once
                settings.edit().putString("versionname", versionName).commit();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.quickMeasurementButton:
                intent = new Intent(this, HeartRateMonitor.class);
                startActivity(intent);
                break;


        }
    }

    public void runTutorial() {
        ArrayList<TutorialEntry> entries = new ArrayList<TutorialEntry>();
        entries.add(new TutorialEntry(R.drawable.phone, getString(R.string.tutorial_1)));
        entries.add(new TutorialEntry(R.drawable.handposition,getString(R.string.tutorial_2)));
        Intent intent = new Intent(getApplicationContext(), TutorialsCardActivity.class);
        intent.putExtra("entries", entries);
        startActivity(intent);
    }
}
