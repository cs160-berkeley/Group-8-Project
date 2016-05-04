package com.cs160.team8.ally;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WaitOne extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_one);
        Typeface main_type = Typeface.createFromAsset(getAssets(), "Quicksand-Bold.otf");
        TextView title_text = (TextView) findViewById(R.id.one);
        title_text.setTypeface(main_type);
        RelativeLayout wait1 = (RelativeLayout) findViewById(R.id.waitone);
        wait1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handle = new Handler();
                handle.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent = new Intent(WaitOne.this, FoundHelp.class);
                        startActivity(intent);
                    }
                }, 1000);
            }
            });
    }
}

