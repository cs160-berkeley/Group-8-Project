package com.cs160.team8.ally;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;

public class PhoneToWatchService extends WearableListenerService implements GoogleApiClient.ConnectionCallbacks {

    private static GoogleApiClient mApiClient;
    private  String path;
    private  byte[] message;
    private Context context;
    private static final String PROFILE_PATH = "/PROFILE";
    private static final String IMAGE_KEY = "IMAGE";
    private static final String PROFILE_KEY = "PROFILE_INFO";

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


    public void sendProfile (Context context, Profile profile) {
        mApiClient = new GoogleApiClient.Builder(context).addApi(Wearable.API).addConnectionCallbacks(this).build();
        mApiClient.connect();
        PutDataMapRequest dataMap = PutDataMapRequest.create(PROFILE_PATH);

//        dataMap.getDataMap().putAsset(IMAGE_KEY, profileWrapper.getAsset());
        dataMap.getDataMap().putByteArray(IMAGE_KEY, toBytes(profile.getPhoto()));

        // Send profile info and bitmap image separately, since bitmaps aren't serializable
        dataMap.getDataMap().putByteArray(PROFILE_KEY, SerializationUtils.serialize(profile.getProfileInfo()));
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

    // Messaging methods. Will be used later to open new activities.

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
//        System.out.println("------------------message received by phone------------------------");
//        path = messageEvent.getPath();
//        message = messageEvent.getData();

    }

    public void sendMessage(Context givenContext) {
//        this.context = givenContext;
//        mApiClient = new GoogleApiClient.Builder(givenContext).addApi(Wearable.API).addConnectionCallbacks(this).build();
//        mApiClient.connect();

    }

    public void sendMessage(final String newPath, final String text, final Context context) {
//        Log.d("SENDING", "MESSAGE");
//        Log.d("SENDING", "MESSAGE");
//
//        setPath(path);
//        setMessage(text.getBytes());
//
//        mApiClient = new GoogleApiClient.Builder(context).addApi(Wearable.API).addConnectionCallbacks(new PhoneToWatchService(path, message)).build();
//        mApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
//        System.out.println("Connected");
//        Log.d("SENDING", "MESSAGE");
//
//
//        System.out.println(" I'm about to send " + this.path);
//        System.out.println(" I'm about to send "  + new String(message));
//
//
//        new Thread( new Runnable() {
//            @Override
//            public void run() {
//                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mApiClient ).await();
//                for(Node node : nodes.getNodes()) {
//                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
//                            mApiClient, node.getId(), path, message ).await();
//                }
//                mApiClient.disconnect();
//            }
//        }).start();
//
//        System.out.println("Sent");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public String getPath() {
        return path;
    }

    public byte[] toBytes (Bitmap bm) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
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
