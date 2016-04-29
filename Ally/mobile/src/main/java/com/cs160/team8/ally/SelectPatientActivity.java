package com.cs160.team8.ally;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Iterator;

public class SelectPatientActivity extends AppCompatActivity {
    static final String PATIENT_ID = "selected-patient-id";
    private Patient selectedPatient;
    private Button connectPatientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_patient);

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
        while (patients.hasNext()) {
            final Patient patient = patients.next();
            Log.d("SelectPatient", "Generating option for " + patient.name);

            if (selectedPatient == null) {
                selectPatient(patient);
            }

            View container = getLayoutInflater().inflate(R.layout.patient_option, null);

            ImageView photo = (ImageView) container.findViewById(R.id.patient_photo);
            photo.setImageBitmap(patient.getImage());

            TextView name = (TextView) container.findViewById(R.id.patient_name);
            name.setText(patient.abbreviatedName());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        Bitmap sallyPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.evan);
        Patient sally = new Patient("Sally Miller", sallyPhoto, 20);
        sally.save();

        Bitmap johnPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.jeremy);
        Patient john = new Patient("John Smith", johnPhoto, 20);
        john.save();

        // Create Visitors
        Bitmap evanPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.evan);
        Bitmap chloePhoto = BitmapFactory.decodeResource(getResources(), R.drawable.chloe);
        Bitmap jeremyPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.jeremy);


        Visitor evan = new Visitor("Evan Miller", sally.getId(), "Grandson", evanPhoto, 9);
        Visitor chloe = new Visitor("Chloe Stanson", sally.getId(), "Caretaker", chloePhoto, 26);
        Visitor jeremy = new Visitor("Jeremy Miller", sally.getId(), "Son", jeremyPhoto, 42);

        evan.save();
        chloe.save();
        jeremy.save();

        // TODO: create reminders for Sally here
    }

    public void connectToPatient(View view) {
        Log.d("SelectPatient", "Connecting with " + selectedPatient.name);
        Intent intent = new Intent(SelectPatientActivity.this, MainActivity.class);
        intent.putExtra(PATIENT_ID, selectedPatient.getId());
        startActivity(intent);
    }
}
