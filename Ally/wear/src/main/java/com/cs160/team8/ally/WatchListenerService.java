package com.cs160.team8.ally;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String reminder = "/REMIND";
    private static final String profile = "/PROFILE";
    private static final String map = "/MAP";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

//        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
//
//        if (messageEvent.getPath().equalsIgnoreCase(SENATOR_INFO)) {
//            String information = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//            Log.d("T", "in WatchListenerService, message received: " + information);
//            String[] parts = information.split("\\+");
//            String[] names = parts[0].split(":");
//            String[] parties = parts[1].split(":");
//            String location = parts[2];

//        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        System.out.println(messageEvent.getPath());
        if (messageEvent.getPath().equalsIgnoreCase(reminder)) {
            String information = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Log.d("T", "in WatchListenerService, message received: " + information);
//            String[] parts = information.split("\\+");
//            String[] names = parts[0].split(":");
//            String[] parties = parts[1].split(":");
//            String location = parts[2];

            Intent intent = new Intent(this, ReminderActivity.class);
//
//            //you need to add this flag since you're starting a new activity from a service
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            Bundle extras = new Bundle();
//            extras.putStringArray(DisplaySenatorActivity.NAMES_KEY, names);
//            extras.putStringArray(DisplaySenatorActivity.PARTIES_KEY, parties);
//            extras.putString(DisplaySenatorActivity.LOCATION_KEY, location);
//
//            intent.putExtras(extras);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//        } else {
//            super.onMessageReceived( messageEvent );
//        }
        } else if (messageEvent.getPath().equalsIgnoreCase(profile)) {
            String information = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Log.d("T", "in WatchListenerService, message received: " + information);
//            String[] parts = information.split("\\+");
//            String[] names = parts[0].split(":");
//            String[] parties = parts[1].split(":");
//            String location = parts[2];

            Intent intent = new Intent(this, ProfileActivity.class);
//
//            //you need to add this flag since you're starting a new activity from a service
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            Bundle extras = new Bundle();
//            extras.putStringArray(DisplaySenatorActivity.NAMES_KEY, names);
//            extras.putStringArray(DisplaySenatorActivity.PARTIES_KEY, parties);
//            extras.putString(DisplaySenatorActivity.LOCATION_KEY, location);
//
//            intent.putExtras(extras);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
//        } else {
//            super.onMessageReceived( messageEvent );
//        }
        } else if (messageEvent.getPath().equalsIgnoreCase(map)) {
                String information = new String(messageEvent.getData(), StandardCharsets.UTF_8);
                Log.d("T", "in WatchListenerService, message received: " + information);
//            String[] parts = information.split("\\+");
//            String[] names = parts[0].split(":");
//            String[] parties = parts[1].split(":");
//            String location = parts[2];

                Intent intent = new Intent(this, LostActivity.class );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            //you need to add this flag since you're starting a new activity from a service
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//            Bundle extras = new Bundle();
//            extras.putStringArray(DisplaySenatorActivity.NAMES_KEY, names);
//            extras.putStringArray(DisplaySenatorActivity.PARTIES_KEY, parties);
//            extras.putString(DisplaySenatorActivity.LOCATION_KEY, location);
//
//            intent.putExtras(extras);
                startActivity(intent);
//        } else {
//            super.onMessageReceived( messageEvent );
//        }

            } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
