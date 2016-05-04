package com.cs160.team8.ally;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Date;



/*


    Used to send messages / data objects to the watch.

    Usage for pushing a profile:

            new PhoneToWatchService().sendProfile(<PROFILE OBJECT>);


 */

public class PhoneToWatchService extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks {

    private static GoogleApiClient mApiClient;
    private  String path;
    private  byte[] message;
    private Context context;
    private static final String PROFILE_PATH = "/PROFILE";
    private static final String PROFILE_KEY = "PROFILE_INFO";
    private static final String MEDICATION_REMINDER_PATH = "/MEDICATION_REMINDER";
    private static final String HELP_REQUESTED_PATH = "/HELP";

    public PhoneToWatchService() {
        this.path = "sample";
        this.message = "sample".getBytes();
    }

    public PhoneToWatchService(String path, String message) {
        this.path = path;
        this.message = message.getBytes();
    }
    public PhoneToWatchService(String path, byte[] message) {
        this.path = path;
        this.message = message;
    }


    public void sendProfile (Context context, Visitor profile) {
        Log.d("SendProfile", "Sending " + profile.name + " to the watch");
        mApiClient = new GoogleApiClient.Builder(context).addApi(Wearable.API).addConnectionCallbacks(this).build();
        mApiClient.connect();
        PutDataMapRequest dataMap = PutDataMapRequest.create(PROFILE_PATH);

        dataMap.getDataMap().putByteArray(PROFILE_KEY, SerializationUtils.serialize(profile));
        dataMap.getDataMap().putLong("time", new Date().getTime());
        PutDataRequest request = dataMap.asPutDataRequest();
        Wearable.DataApi.putDataItem(mApiClient, request)
                .setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                    @Override
                    public void onResult(DataApi.DataItemResult dataItemResult) {
                        Log.d("PhoneToWatchService:", "Sending profile was successful: " + dataItemResult.getStatus()
                                .isSuccess());
                    }
                });


    }

    public void sendMedicationReminder(Context context, Medication medication) {
        this.path = MEDICATION_REMINDER_PATH;
        this.message = SerializationUtils.serialize(medication);

        sendMessage(context);
    }

    public void openPressForHelpScreen(Context context) {
        this.path = "PRESS_FOR_HELP";
        this.message = "sample".getBytes();
        sendMessage(context);
    }

    // Messaging methods. Will be used later to open new activities.

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        System.out.println("------------------message received by phone------------------------");
        String path = messageEvent.getPath();
        String message = new String(messageEvent.getData(), StandardCharsets.UTF_8);

        Log.d("WatchMessage", path + ": " + message);

        if (path.equalsIgnoreCase(HELP_REQUESTED_PATH)) {
            System.out.println("Help has been requested!");
            sendHelpRequestedNotification();
            Intent intent = new Intent(this, PatientLocationActivity.class);
            //you need to add this flag since you're starting a new activity from a service
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    private void sendHelpRequestedNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(getBaseContext(), PatientLocationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
        android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
                .setTicker("Ally").setContentTitle("Patient requested help!")
                .setContentText("Your patient has requested help! Open the map to v.")
                .setSmallIcon(R.drawable.ally_logo)
                .setContentIntent(pIntent);
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(notificationSound);
        Notification noti = builder.build();
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, noti);
    }

    public void sendMessage(Context givenContext) {
        this.context = givenContext;
        mApiClient = new GoogleApiClient.Builder(givenContext).addApi(Wearable.API).addConnectionCallbacks(this).build();
        mApiClient.connect();

    }

    public void sendMessage(final String newPath, final String text, final Context context) {
        Log.d("SENDING", "MESSAGE");
        setPath(path);
        setMessage(text.getBytes());

        mApiClient = new GoogleApiClient.Builder(context).addApi(Wearable.API).addConnectionCallbacks(new PhoneToWatchService(path, message)).build();
        mApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        System.out.println("Connected");

        System.out.println(" I'm about to send " + this.path);
        System.out.println(" I'm about to send "  + new String(message));


        new Thread( new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mApiClient ).await();
                for(Node node : nodes.getNodes()) {
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mApiClient, node.getId(), path, message ).await();
                }
                mApiClient.disconnect();
            }
        }).start();

        System.out.println("Sent");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public String getPath() {
        return path;
    }

    public byte[] toBytes (Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, stream);
        return stream.toByteArray();
    }

    public byte[] getMessage() {
        return message;
    }

    public void setPath(final String newPath) {
        this.path = newPath;
    }

    public void setMessage(final byte[] newMessage) {
        this.message = newMessage;
    }

    public static int byteArrayToLeInt(byte[] b) {
        final ByteBuffer bb = ByteBuffer.wrap(b);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        return bb.getInt();
    }
}
