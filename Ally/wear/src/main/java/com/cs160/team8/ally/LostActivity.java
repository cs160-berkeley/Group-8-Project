package com.cs160.team8.ally;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        Button no_button = (Button) findViewById(R.id.No);
        Button yes_button = (Button) findViewById(R.id.Yes);
//        ImageButton wait1 = (ImageButton) findViewById(R.id.lost);
        no_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LostActivity.this, PressHelp.class);
                startActivity(intent);
            }
        });
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LostActivity.this, PressHelp.class);
                startActivity(intent);
            }
        });
    }
}
