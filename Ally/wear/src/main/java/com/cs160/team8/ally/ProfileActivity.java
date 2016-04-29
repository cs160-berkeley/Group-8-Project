package com.cs160.team8.ally;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Activity {

    private Typeface main_type;
    private ImageView profilePhoto;
    private TextView profileName;
    private TextView profileInfo;
    private Visitor visitorProfile;

    /**
     * Created by KunalPatel on 4/17/16.
     */

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile);

            visitorProfile = (Visitor) getIntent().getSerializableExtra(WatchToPhoneService.PROFILE_KEY);
            Log.d("ProfileActivity", "Loaded profile of " + visitorProfile.name);

            main_type = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Bold.ttf");

            profilePhoto = (ImageView) findViewById(R.id.profile_photo);
            profilePhoto.setImageBitmap(visitorProfile.getImage());

            profileName = (TextView) findViewById(R.id.profile_name);
            profileName.setText(visitorProfile.name);

            profileInfo = (TextView) findViewById(R.id.profile_info);
            profileInfo.setText(visitorProfile.relationship + ", " + visitorProfile.age);
//        profileName.setTypeface(main_type);
//        profileInfo.setTypeface(main_type);
        }
    }

