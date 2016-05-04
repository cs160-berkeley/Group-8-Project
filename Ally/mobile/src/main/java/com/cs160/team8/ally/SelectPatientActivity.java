package com.cs160.team8.ally;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectPatientActivity extends AppCompatActivity {
    static final String PATIENT_ID = "selected-patient-id";
    private Patient selectedPatient;
    private Button connectPatientButton;
    View lastBorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_patient);

        Typeface main_type = Typeface.createFromAsset(getAssets(), "Quicksand-Regular.otf");
        Typeface lato = Typeface.createFromAsset(getAssets(), "Lato2OFL/Lato-Regular.ttf");
        TextView addProfile = (TextView) findViewById(R.id.add_patient);
        addProfile.setTypeface(lato);

        connectPatientButton = (Button) findViewById(R.id.patient_connect_button);
        connectPatientButton.setTypeface(lato);
        connectPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhoneToWatchService().openPressForHelpScreen(getApplicationContext());
                connectToPatient();
            }
        });

        Patient.deleteAll(Patient.class);
        long numPatients = Patient.count(Patient.class);
        if (numPatients == 0) {
            seedDatabase();
        }

        LinearLayout patientsContainer = (LinearLayout) findViewById(R.id.patients_container);
        connectPatientButton = (Button) findViewById(R.id.patient_connect_button);
        // Prevent button from forcing text to all caps
        connectPatientButton.setTransformationMethod(null);

        Iterator<Patient> patients = Patient.findAll(Patient.class);
        int i = 0;
        while (patients.hasNext()) {
            final Patient patient = patients.next();
            Log.d("SelectPatient", "Generating option for " + patient.name);

            if (selectedPatient == null) {
                selectPatient(patient);
            }

            View container = getLayoutInflater().inflate(R.layout.patient_option, null);

            final View border = (View) container.findViewById(R.id.image_border);
            ImageView photo = (ImageView) container.findViewById(R.id.patient_photo);
            photo.setImageBitmap(patient.getImage());
            if (i == 0) {
                lastBorder = border;
                border.setVisibility(View.VISIBLE);
                i++;
            }

            TextView name = (TextView) container.findViewById(R.id.patient_name);
            name.setText(patient.abbreviatedName());
            name.setTypeface(lato);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (border.getVisibility() == View.INVISIBLE) {
                        lastBorder.setVisibility(View.INVISIBLE);
                        border.setVisibility(View.VISIBLE);
                        lastBorder = border;
                    }
                    selectPatient(patient);
                }
            });

            patientsContainer.addView(container);
        }
    }

    private void selectPatient(Patient patient) {
        selectedPatient = patient;
        connectPatientButton.setText("Connect with " + patient.abbreviatedName());
    }

    private void seedDatabase() {
        // Create Patients
        Bitmap seanPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.sean);
        Patient sean = new Patient("Sean Nguyen", seanPhoto, 20);
        sean.save();

        Bitmap johnPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.john_photo);
        Patient john = new Patient("John Smith", johnPhoto, 20);
        john.save();

        // Create Visitors
        Bitmap evanPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.evan);
        Bitmap chloePhoto = BitmapFactory.decodeResource(getResources(), R.drawable.chloe);
        Bitmap jeremyPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.jeremy);


        Visitor evan = new Visitor("Evan Miller", sean.getId(), "Grandson", evanPhoto, 9);
        Visitor chloe = new Visitor("Chloe Stanson", sean.getId(), "Caretaker", chloePhoto, 26);
        Visitor jeremy = new Visitor("Jeremy Miller", sean.getId(), "Son", jeremyPhoto, 42);

        evan.save();
        chloe.save();
        jeremy.save();

        // TODO: create reminders for Sally here
        List<Integer> everyDay = new ArrayList<Integer>();
        for (int i = 0; i < 7; i += 1) {
            everyDay.add(i);
        }

        Reminder heartPill = new Reminder(sean, "Take heart pill", 14, 30, everyDay, true, true, true);
        Reminder takeWalk = new Reminder(sean, "Go for a walk", 15, 00, everyDay, true, true, true);

        heartPill.save();
        takeWalk.save();
    }

    public void connectToPatient() {
        Log.d("SelectPatient", "Connecting with " + selectedPatient.name);
        Intent intent = new Intent(SelectPatientActivity.this, MainActivity.class);
        intent.putExtra(PATIENT_ID, selectedPatient.getId());
        startActivity(intent);
    }
}
