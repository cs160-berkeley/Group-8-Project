package com.cs160.team8.ally;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WaitTwo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_two);
        RelativeLayout wait2 = (RelativeLayout) findViewById(R.id.waittwo);
        Typeface main_type = Typeface.createFromAsset(getAssets(), "Quicksand-Bold.otf");
        TextView title_text = (TextView) findViewById(R.id.two);
        title_text.setTypeface(main_type);
        wait2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitTwo.this, WaitOne.class);
                startActivity(intent);
            }
        });
    }
}
