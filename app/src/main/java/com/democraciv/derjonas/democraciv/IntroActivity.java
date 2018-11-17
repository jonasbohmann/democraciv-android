package com.democraciv.derjonas.democraciv;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * DEMOCRACIV APP BY DerJonas (u/Jovanos)
 * Version 1.4-release
 * Contact me on Discord or Reddit
 */

public class IntroActivity extends AppIntro {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //GPDR CONSENT
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("We use the Firebase-Messassing Service to send push notifications to our users. During this process, your app's Instance ID is sent to the Firebase servers to be processed. The developer of this app is not able to view or share this Instance ID. Firebase may also (without our influence) collect and store other information such as your Android and App version, your type of device and the country your device is located in. In accordance with the GDPR, you have to give us consent to send this data to Firebase to enable push notifications.");
        builder1.setCancelable(false);
        builder1.setTitle("General Data Protection Regulation (GDPR)");
        builder1.setIcon(R.drawable.ic_gdpr);
        builder1.setPositiveButton(
                "I give consent.",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "I don't give consent.",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getBaseContext(), NoConsentActivity.class);
                        startActivity(intent);

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

        //IntroSlides
        addSlide(AppIntroFragment.newInstance("Welcome!", "Greetings to Democraciv!", R.drawable.ic_slide0, ContextCompat.getColor(getApplicationContext(), R.color.slide0)));

        addSlide(AppIntroFragment.newInstance("Explore", "Democraciv is a community dedicated to playing a singleplayer game of Sid Meier\'s Civilization V using democratic principles.", R.drawable.ic_slide1, ContextCompat.getColor(getApplicationContext(), R.color.slide1)));

        addSlide(AppIntroFragment.newInstance("Discover", "You can take part in a simulated government, political parties, the press, and even in an economy with banks, a stock market and companies!", R.drawable.ic_slide2, ContextCompat.getColor(getApplicationContext(), R.color.slide2)));

        addSlide(AppIntroFragment.newInstance("Getting started", "Our community is on Reddit and Discord. This app and the Beginner's Guide are the perfect start into Democraciv with every information you need.", R.drawable.ic_slide3, ContextCompat.getColor(getApplicationContext(), R.color.slide3)));

        addSlide(AppIntroFragment.newInstance("Have fun!", "See for yourself and have fun with Democraciv!", R.drawable.ic_slide4, ContextCompat.getColor(getApplicationContext(), R.color.slide4)));

        showStatusBar(false);


    }

    //Skip Button
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }
    //Done Button am Ende
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }



}