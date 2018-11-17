package com.democraciv.derjonas.democraciv;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * DEMOCRACIV APP BY DerJonas (u/Jovanos)
 * Version 1.4-release
 * Contact me on Discord or Reddit
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        //Build Notification wenn App in Foreground
        Notification notification = new NotificationCompat.Builder(this, "defaultnotficiationchannel")
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("body"))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("body")))
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .build();

                //Open URLs in Browser
                String noturl = remoteMessage.getData().get("url");
                if(noturl!= null) {
                    notification.contentIntent = PendingIntent.getActivity(this, 0,
                            new Intent(Intent.ACTION_VIEW, Uri.parse(noturl)), PendingIntent.FLAG_UPDATE_CURRENT);
                }

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(123, notification);


        }
    }