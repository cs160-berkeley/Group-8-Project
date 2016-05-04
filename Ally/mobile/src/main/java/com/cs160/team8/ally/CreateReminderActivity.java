package com.cs160.team8.ally;

import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateReminderActivity extends AppCompatActivity {

    private Typeface lato;
    Button you_button;
    Button patient_button;
    boolean you_filled = false;
    boolean patient_filled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);

        lato = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Regular.ttf");
        setTitle("New Reminder");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

//        EditText editText = (EditText) findViewById(R.id.edit_title);
//        editText.setTypeface(lato);
//        TextView reminderFor = (TextView) findViewById(R.id.reminder_for);
//        reminderFor.setTypeface(lato);


        you_button = (Button) findViewById(R.id.you_button);
        patient_button = (Button) findViewById(R.id.patient_button);
        you_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onClick", "in onClick");
                if (you_filled) {
                    Log.i("onClick", "background is filled");
                    you_button.setBackground(getDrawable(R.drawable.rounded_button_stroked));
                    you_button.setTextColor(getResources().getColor(R.color.colorPrimary));
                    you_filled = !you_filled;
                }
                else if (!you_filled) {
                    Log.i("onClick", "background is not filled");
                    you_button.setBackground(getDrawable(R.drawable.rounded_button));
                    you_button.setTextColor(Color.WHITE);
                    you_filled = !you_filled;
                }

            }
        });

        patient_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (patient_filled) {
                    patient_button.setBackground(getDrawable(R.drawable.rounded_button_stroked));
                    patient_button.setTextColor(getResources().getColor(R.color.colorPrimary));
                    patient_filled = !patient_filled;
                }
                else if (!patient_filled) {
                    patient_button.setBackground(getDrawable(R.drawable.rounded_button));
                    patient_button.setTextColor(Color.WHITE);
                    patient_filled = !patient_filled;
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_done:
                break;

        }
        return true;
    }

}
