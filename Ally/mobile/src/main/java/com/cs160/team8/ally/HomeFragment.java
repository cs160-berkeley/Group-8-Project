package com.cs160.team8.ally;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


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
    static Patient patient;

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
    public static HomeFragment newInstance(Patient p) {
        HomeFragment fragment = new HomeFragment();
        patient = p;
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
        LinearLayout fragContainer = (LinearLayout) getActivity().findViewById(R.id.mapfragmentcontainer);

//        LinearLayout ll = new LinearLayout(getActivity());
//        ll.setOrientation(LinearLayout.HORIZONTAL);
//
//        ll.setId(1);


//        fragContainer.addView(ll);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = (Button) view.findViewById(R.id.message_patient_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                messagePatient();
            }
        });
        TextView name = (TextView)  view.findViewById(R.id.patientname);
        TextView reminder = (TextView) view.findViewById(R.id.pillreminder);
        TextView remind = (TextView) view.findViewById(R.id.remind);
        TextView dismiss = (TextView) view.findViewById(R.id.dismiss);
        TextView range = (TextView) view.findViewById(R.id.is_in_range);
        remind.setText("REMIND");
        dismiss.setText("DISMISS");
        range.setText(patient.name + " is in the safe zone.");
        reminder.setText(patient.name + " was supposed to take their heart pill 30 minutes ago!");
        button.setText("Message Patient");
        name.setText(patient.name);
        ImageView photo = (ImageView) view.findViewById(R.id.patient_profile_photo);
        photo.setImageBitmap(patient.getImage());
        Typeface main_type = Typeface.createFromAsset(getActivity().getAssets(), "Quicksand-Regular.otf");
        Typeface lato = Typeface.createFromAsset(getActivity().getAssets(), "Lato2OFL/Lato-Regular.ttf");
        name.setTypeface(lato);
        button.setTypeface(lato);
        reminder.setTypeface(lato);
        dismiss.setTypeface(lato);
        remind.setTypeface(lato);
        range.setTypeface(lato);
//        ImageButton mapButton = (ImageButton) view.findViewById(R.id.patient_location_screen);
//        mapButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), PatientLocationActivity.class);
//                startActivity(intent);
//            }
//        });
        Fragment loc = new LocationFragment();
        getChildFragmentManager().beginTransaction().add(R.id.mapfragmentcontainer, loc, "locationfragment").commit();
        return view;
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
