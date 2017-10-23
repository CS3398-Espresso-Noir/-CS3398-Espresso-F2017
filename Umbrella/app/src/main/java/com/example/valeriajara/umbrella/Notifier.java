package com.example.valeriajara.umbrella;

import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Notifier extends FirebaseMessagingService {

    private long lastNotificationSent = 0; //when notification was sent from firebase in ms
    private static final String TAG = "Notification Service";
    private boolean hasBeenNotified = false;

    @Override
    public void onMessageReceived(RemoteMessage notification) {
        Log.d(TAG, "From: " + notification.getFrom());
        Log.d(TAG, "Notification Message Body: " + notification.getNotification().getBody());
        hasBeenNotified = true;
        lastNotificationSent = (System.currentTimeMillis() - notification.getSentTime());
    }

    public long secondsSinceNotified() {
       return ((System.currentTimeMillis() - lastNotificationSent)/1000);
    }

    public String message() {
        String msg;

        if (!hasBeenNotified) { // output nothing, user has never received a notification
            msg = "";
            return msg;
        }

        msg = "You were last sent a notification ";
        long temp = secondsSinceNotified();
        long hours = 0;
        long secondsLeft = 0;
        long minutesLeft = 0;
        boolean hourPassed = false;
        boolean minutePassed = false;
        boolean secondPassed = false;

        if (temp >= 3600) {
            hours = temp / 3600; //rounds down
            temp -= hours * 3600; //remove the number of full hours in seconds
            secondPassed = true;
            minutePassed = true;
            hourPassed = true;
        }
        if (temp >= 60) {
            minutesLeft = temp / 60; //rounds down
            temp -= minutesLeft * 60; //remove the number of full minutes in seconds
            secondPassed = true;
            minutePassed = true;
        }
        if (temp >= 0) {
            secondsLeft = temp;
            secondPassed = true;
        }

        if (hourPassed) {
            msg += hours + " hours, ";
        }
        if (minutePassed) {
            if (hourPassed) {
                msg += minutesLeft + "minutes, and ";
            } else {
                msg += minutesLeft + " minutes and ";
            }
        }
        if (secondPassed) {
            msg += secondsLeft + " seconds ago.";
        }
        return msg;
    }
}

