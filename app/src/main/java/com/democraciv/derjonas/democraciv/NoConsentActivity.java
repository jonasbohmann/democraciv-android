package com.democraciv.derjonas.democraciv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * DEMOCRACIV APP BY DerJonas (u/Jovanos)
 * Version 1.4-release
 * Contact me on Discord or Reddit
 */

public class NoConsentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_consent);

    }

    public void consentBack(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }
    public void onBackPressed() {

    }
}

