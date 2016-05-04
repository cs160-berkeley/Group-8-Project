package com.cs160.team8.ally;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.data.FreezableUtils;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchToPhoneService extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mWatchApiClient;
    private List<Node> nodes = new ArrayList<>();
    private static final String PROFILE_PATH = "/PROFILE";
    static final String REQUEST_HELP_PATH = "/HELP";
    static final String PROFILE_KEY = "PROFILE_INFO";


    private String path = "sample path";
    private String message = "sample message";


    private byte[] messageBytes;

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize the googleAPIClient for message passing
        mWatchApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(this)
                .build();
        //and actually connect it
        mWatchApiClient.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWatchApiClient.disconnect();
    }


    @Override //alternate method to connecting: no longer create this in a new thread, but as a callback
    public void onConnected(final Bundle bundle) {
        Log.d("T", "in onconnected");
        Wearable.NodeApi.getConnectedNodes(mWatchApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                        nodes = getConnectedNodesResult.getNodes();
                        Log.d("T", "found nodes");
                        //when we find a connected node, we populate the list declared above
                        //finally, we can send a message
                        //sendMessage("/open_details", bundle.getString("name"));
                    }
                });
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getExtras() != null) {
            final Bundle extras = intent.getExtras();

            // Send the message with the cat name
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //first, connect to the apiclient
                    mWatchApiClient.connect();

                    String path = extras.getString("path");

                    if (path.equals(REQUEST_HELP_PATH)) {
                        sendMessage(path, "Help needed");
                    } else if (path.equals("/randomize_location")) {
                        sendMessage(path, path);
                    }
                }
            }).start();
        }

        return START_STICKY;
    }

    @Override //we need this to implement GoogleApiClient.ConnectionsCallback
    public void onConnectionSuspended(int i) {

    }

    private void sendMessage(final String path, final String text ) {
        System.out.println("sending message");
        new Thread( new Runnable() {
            @Override
            public void run() {
                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mWatchApiClient ).await();
                for(Node node : nodes.getNodes()) {
                    //we find 'nodes', which are nearby bluetooth devices (aka emulators)
                    //send a message for each of these nodes (just one, for an emulator)
                    Log.d("T", "sending to path: " + path);
                    Log.d("T", "sending message: " + text);
                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
                            mWatchApiClient, node.getId(), path, text.getBytes() ).await();
                    //4 arguments: api client, the node ID, the path (for the listener to parse),
                    //and the message itself (you need to convert it to bytes.)
                }
            }
        }).start();
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        System.out.println("Watch received a message.");

        path = messageEvent.getPath();
        messageBytes = messageEvent.getData();

        System.out.println(path);


        if (path.equals("/MEDICATION_REMINDER")) {
            Medication medication = (Medication) SerializationUtils.deserialize(messageBytes);
            System.out.println(""+medication.getCount() + medication.getMedicationName());
            Intent openMedicationReminder = new Intent(this, ReminderActivity.class);
            openMedicationReminder.putExtra("MEDICATION", medication);
            openMedicationReminder.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(openMedicationReminder);

        }

        else if (path.equals("PRESS_FOR_HELP")) {

            // Open the PRESS FOR HELP screen

            Intent intent = new Intent(this, PressHelp.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }



    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {

        System.out.println("onDataChanged called!");

        final List<DataEvent> events = FreezableUtils.freezeIterable(dataEvents);
        dataEvents.close();
        for (DataEvent event : events) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                String path = event.getDataItem().getUri().getPath();
                if (WatchToPhoneService.PROFILE_PATH.equals(path)) {
                    DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());

                    byte[] serialized = dataMapItem.getDataMap().getByteArray(WatchToPhoneService.PROFILE_KEY);
                    Visitor profileInfo = (Visitor) SerializationUtils.deserialize(serialized);

                        /*
                            Do stuff with the profile and picture

                         */
                    Intent intent = new Intent(this, ProfileActivity.class);

                    System.out.println("Watch received image!");
                    intent.putExtra(PROFILE_KEY, profileInfo);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            }
        }
    }

}

