package com.cs160.team8.ally;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KunalPatel on 4/17/16.
 */
public class ProfileActivity extends Activity {

    private TextView profileName;
    private ImageView profilePicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        profilePicture = (ImageView) findViewById(R.id.profile_picture);
        profileName = (TextView) findViewById (R.id.profile_name);

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

                this.profilePicture.setImageBitmap(bmp);
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
