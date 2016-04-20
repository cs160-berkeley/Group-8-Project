package com.cs160.team8.ally;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class FoundHelp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_help);
        Typeface main_type = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Bold.ttf");
        TextView help_text = (TextView) findViewById(R.id.found_help);
        help_text.setTypeface(main_type);
    }
}
