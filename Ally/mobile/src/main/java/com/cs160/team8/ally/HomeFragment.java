package com.cs160.team8.ally;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Patient patient;
    MapView mMapView;
    private GoogleMap googleMap;
    LinearLayout remindersContainer;
    Typeface lato;

    public void onFragmentInteraction(Uri uri){

    }

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve arguments passed in newInstance
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        patient = ((MainActivity) getActivity()).currentPatient;
        lato = Typeface.createFromAsset(getActivity().getAssets(), "Lato2OFL/Lato-Regular.ttf");

        remindersContainer = (LinearLayout) view.findViewById(R.id.patient_reminders_container);
        setUpReminders(inflater);

        Button button = (Button) view.findViewById(R.id.message_patient_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                messagePatient();
            }
        });
        TextView name = (TextView)  view.findViewById(R.id.patientname);

        TextView range = (TextView) view.findViewById(R.id.is_in_range);
        range.setText(patient.firstName() + " is in the safe zone.");

//        button.setText("Message Patient");
        name.setText(patient.name);
        ImageView photo = (ImageView) view.findViewById(R.id.patient_profile_photo);
        photo.setImageBitmap(patient.getImage());
        Typeface main_type = Typeface.createFromAsset(getActivity().getAssets(), "Quicksand-Regular.otf");
        Typeface lato = Typeface.createFromAsset(getActivity().getAssets(), "Lato2OFL/Lato-Regular.ttf");
        name.setTypeface(lato);
        button.setTypeface(lato);
        range.setTypeface(lato);

        mMapView = (MapView) view.findViewById(R.id.patient_location_map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setUpPatientLocationMap();

        return view;
    }

    private void setUpReminders(LayoutInflater inflater) {
        View medicationReminder = inflater.inflate(R.layout.medication_reminder, null);
        TextView reminderText = (TextView) medicationReminder.findViewById(R.id.reminder_text);
        TextView remindButton = (TextView) medicationReminder.findViewById(R.id.remind_button);
        TextView dismissButton = (TextView) medicationReminder.findViewById(R.id.dismiss_button);

        reminderText.setText(patient.firstName() + " was supposed to take Lipitor 30 minutes ago!");
        remindButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Send medication reminder to watch
                Medication medication = new Medication("Lipitor", 1);
                new PhoneToWatchService().sendMedicationReminder(getContext(),medication);
            }
        });
        remindButton.setText("REMIND");
        dismissButton.setText("DISMISS");

        reminderText.setTypeface(lato);
        remindButton.setTypeface(lato);
        dismissButton.setTypeface(lato);

        remindersContainer.addView(medicationReminder);
    }

    private void setUpPatientLocationMap() {
        double lat = 37.878091;
        double lon = -122.262124;

        googleMap = mMapView.getMap();
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title(patient.firstName() + "'s Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_icon)));

        // Move the camera instantly  with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(lat, lon), 15));

        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12), 1000, null);

        mMapView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PatientLocationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void messagePatient() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_message_patient, null);
        final EditText messageText = (EditText) view.findViewById(R.id.message_text);

        builder.setView(view)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("MessagePatient", messageText.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("MessagePatient", "Message cancelled");
                    }
                });

        // Create the AlertDialog object and show it
        builder.create().show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
