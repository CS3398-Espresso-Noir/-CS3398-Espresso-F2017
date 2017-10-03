package com.example.valeriajara.umbrella;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notifier extends FirebaseMessagingService {

    private static final String TAG = "Notification Service";

    @Override
    public void onMessageReceived(RemoteMessage notification) {
        Log.d(TAG, "From: " + notification.getFrom());
        Log.d(TAG, "Notification Message Body: " + notification.getNotification().getBody());
    }
}

