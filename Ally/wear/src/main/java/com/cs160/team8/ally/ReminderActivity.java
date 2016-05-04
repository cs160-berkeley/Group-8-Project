package com.cs160.team8.ally;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReminderActivity extends Activity {

    private TextView mTextView;

    private Typeface main_type;
    private TextView reminderTitle;
    private ImageButton reminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });

        main_type = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Bold.ttf");
        reminderTitle = (TextView) findViewById(R.id.reminder_title);
        reminderButton = (ImageButton) findViewById(R.id.reminder_button);

//        reminderTitle.setText("Take one heart pill");
        reminderTitle.setTypeface(main_type);

        loadMedicationReminder();
        setReminderButtonListener();
    }

    public void loadMedicationReminder() {
        Medication medication = (Medication) getIntent().getExtras().get("MEDICATION");
        if (medication != null) {
            this.reminderTitle.setText("Take " + medication.getCount()+ " " + medication.getMedicationName());
        }
    }

    private void setReminderButtonListener() {
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Return to presshelp screen
                Intent returnToPressHelp = new Intent(getApplicationContext(), PressHelp.class);
                startActivity(returnToPressHelp);


                //Tell the phone that the medication was taken.

            }
        });
    }
}
