package com.democraciv.derjonas.democraciv;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;

/**
 * DEMOCRACIV APP BY DerJonas (u/Jovanos)
 * Version 1.4-release
 * Contact me on Discord or Reddit
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

}


