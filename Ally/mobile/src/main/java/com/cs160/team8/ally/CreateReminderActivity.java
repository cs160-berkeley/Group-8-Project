package com.cs160.team8.ally;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CreateReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        setTitle("New Reminder");
    }
}
