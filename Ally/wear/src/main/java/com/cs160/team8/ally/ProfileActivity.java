package com.cs160.team8.ally;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity {

    private TextView mTextView;

    private Typeface main_type;
    private ImageView profilePhoto;
    private TextView profileName;
    private TextView profileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        main_type = Typeface.createFromAsset(getAssets(), "Lato-Bold.ttf");
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
        profileName = (TextView) findViewById(R.id.profile_name);
        profileInfo = (TextView) findViewById(R.id.profile_info);
        profileName.setTypeface(main_type);
        profileInfo.setTypeface(main_type);

        profilePhoto.setImageResource(R.drawable.jeremy_miller);
        profileName.setText("Jeremy Miller");
        profileInfo.setText("Son, 42");

    }
}
