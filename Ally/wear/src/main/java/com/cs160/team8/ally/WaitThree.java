package com.cs160.team8.ally;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WaitThree extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_three);
        RelativeLayout wait3 = (RelativeLayout) findViewById(R.id.waitthree);
        Typeface main_type = Typeface.createFromAsset(getAssets(), "Quicksand-Bold.otf");
        TextView title_text = (TextView) findViewById(R.id.three);
        title_text.setTypeface(main_type);
        wait3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WaitThree.this, WaitTwo.class);
                startActivity(intent);
            }
        });
    }
}
