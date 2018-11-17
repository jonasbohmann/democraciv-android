package com.democraciv.derjonas.democraciv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.view.View;

/**
 * DEMOCRACIV APP BY DerJonas (u/Jovanos)
 * Version 1.4-release
 * Contact me on Discord or Reddit
 */

public class SettingsFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_main);
        Preference pref_launch_settings = (Preference) findPreference("pref_launch_settings");
        pref_launch_settings.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener(){
            public boolean onPreferenceClick(Preference preference){
                Intent intent = new Intent();
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", getActivity().getPackageName());
                intent.putExtra("app_uid", getActivity().getApplicationInfo().uid);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    intent.putExtra("android.provider.extra.APP_PACKAGE", getActivity().getPackageName());
                }
                startActivity(intent);
                return true;
            }
        });

    }

}