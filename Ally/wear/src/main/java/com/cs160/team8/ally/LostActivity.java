package com.cs160.team8.ally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.ImageButton;

public class LostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        ImageButton wait1 = (ImageButton) findViewById(R.id.lost);
        wait1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LostActivity.this, PressHelp.class);
                startActivity(intent);
            }
        });
    }
}
