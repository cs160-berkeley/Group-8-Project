package com.cs160.team8.ally;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReminderActivity extends Activity {

    private TextView mTextView;

    private TextView reminderTitle;
    private ImageButton reminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        reminderTitle = (TextView) findViewById(R.id.reminder_title);
        reminderTitle.setText("Take one heart pill");
        reminderButton = (ImageButton) findViewById(R.id.reminder_button);
    }
}
