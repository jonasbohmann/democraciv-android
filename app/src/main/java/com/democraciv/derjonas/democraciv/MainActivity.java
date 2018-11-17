package com.democraciv.derjonas.democraciv;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * DEMOCRACIV APP BY DerJonas (u/Jovanos)
 * Version 1.4-release
 * Contact me on Discord or Reddit
 */



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    //Prefs für FirstStart Intro
    SharedPreferences prefs = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Main Activity and Toolbar
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //WebView, Homepage
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://reddit.com/r/democraciv/");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(false);
        myWebView.setWebViewClient(new WebViewClient());

        //Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Prefs für FirstStart()
        prefs = getSharedPreferences("com.democraciv.derjonas.democraciv", MODE_PRIVATE);


        //IntroActivity FirstStartCheck
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //Initialize SharedPreferences
                SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

                boolean isFirstStartup = getPrefs.getBoolean("firstStartupofApp", true);

                if (isFirstStartup) {

                    //Launch app intro
                    final Intent i = new Intent(MainActivity.this, IntroActivity.class);
                    runOnUiThread(new Runnable() {
                        @Override public void run() {
                            startActivity(i);
                        }
                    });
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putBoolean("firstStartupofApp", false);
                    e.apply();
                }
            }
        });

        // Start the thread
        t.start();


        //Notification Topic fuer FCM
        FirebaseMessaging.getInstance().subscribeToTopic("global");

        //Prefs für Settings
        PreferenceManager.setDefaultValues(this, R.xml.pref_main, false);


        //Notificationchannel fuer Android O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            CharSequence name = "Notifications";
            String description = "Every Notification from this app.";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel("defaultnotficiationchannel", "Notifications", NotificationManager.IMPORTANCE_HIGH);
            mChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(mChannel);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.onPause();
        myWebView.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.destroy();
        super.onDestroy();
    }
    //onResume
    @Override
    protected void onResume() {
        super.onResume();
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.onResume();
        myWebView.resumeTimers();
    }

    //Ab hier fuer Drawer
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView myWebView = (WebView) findViewById(R.id.webview);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //3Dot Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myWebView.getUrl()));
            startActivity(browserIntent);
            return true;
        }

        if (id == R.id.action_info) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Ab hier Links/WebViews für Drawer Items
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://reddit.com/r/democraciv/");
        } else if (id == R.id.nav_reddit) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://reddit.com/r/democraciv");
        } else if (id == R.id.nav_discord) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://discord.gg/AK7dYMG");
        } else if (id == R.id.nav_twitch) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://www.twitch.tv/democraciv");
        } else if (id == R.id.nav_bguide){
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://www.reddit.com/r/democraciv/wiki/beginnersguide");
        } else if (id == R.id.nav_const) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://docs.google.com/document/d/1d2uVJ5XmFToymk6kBWOPTWiTGTo7MEqYunMxH4qZ4ok/edit?usp=sharing");
        } else if (id == R.id.nav_schedule) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://www.reddit.com/r/democraciv/wiki/schedule");
        } else if (id == R.id.nav_parties) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://www.reddit.com/r/democraciv/wiki/party");
        } else if (id == R.id.nav_wiki) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://www.reddit.com/r/democraciv/wiki/index");
        } else if (id == R.id.nav_laws) {
            WebView myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadUrl("https://docs.google.com/document/d/1r7n7PMK8dPuLTooLW8_dBNmuTbvKP1teT_oBDgDacVs/edit?usp=drivesdk");
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
