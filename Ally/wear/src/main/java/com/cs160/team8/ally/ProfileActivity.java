package com.cs160.team8.ally;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
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
//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });

        main_type = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Bold.ttf");
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
        profileName = (TextView) findViewById(R.id.profile_name);
        profileInfo = (TextView) findViewById(R.id.profile_info);
//        profileName.setTypeface(main_type);
//        profileInfo.setTypeface(main_type);
//
//        profilePhoto.setImageResource(R.drawable.jeremy_miller);
//        profileName.setText("Jeremy Miller");
//        profileInfo.setText("Son, 42");
        loadProfilePicture();
        loadProfileName();

        /*
            Forced profile loading: proof of work for prog 03
            pro
         */

    }

    private void loadProfilePicture(){

        byte[] data = getIntent().getByteArrayExtra("PROFILE_PICTURE");

        if (data != null) {

            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);

            if (bmp != null) {

                this.profilePhoto.setImageBitmap(bmp);
            }
        }
        else {
            System.err.println("IMAGE IS NULL");
        }
    }

    private void loadProfileName() {

        ProfileInfo profInfo = (ProfileInfo) getIntent().getSerializableExtra("PROFILE_INFO");
        this.profileName.setText(profInfo.getName());


    }

}
