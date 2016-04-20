package com.cs160.team8.ally;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PressHelp extends Activity {

    private TextView mTextView;
    TextView help_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_help);
        Typeface main_type = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Bold.ttf");
        help_text = (TextView) findViewById(R.id.title);
        help_text.setTypeface(main_type);
        RelativeLayout press = (RelativeLayout) findViewById(R.id.press);
        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PressHelp.this, WaitThree.class);
                startActivity(intent);
            }
        });
    }
}
