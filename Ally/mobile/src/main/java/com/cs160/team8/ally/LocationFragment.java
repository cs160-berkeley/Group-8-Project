package com.cs160.team8.ally;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 *Create a map fragment on the home screen
 */
public class LocationFragment extends Fragment {
    private static View view;
    GoogleMap googleMap;
    private static View v;
    private OnFragmentInteractionListener mListener;
    public void onFragmentInteraction(Uri uri){

    }
    public LocationFragment() {
        // Required empty public constructor
    }

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (v==null) {
            v = inflater.inflate(R.layout.fragment_location, container, false);
        }

        System.out.println(v);
        double lat = 37.878091;
        double lon = -122.262124;
        System.out.println(getChildFragmentManager().findFragmentById((R.id.mapfragment)));
        if (getChildFragmentManager().findFragmentById((R.id.mapfragment)) != null) {
            googleMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment)).getMap();

            MapsInitializer.initialize( v.getContext());

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lon))
                    .title("Sean's Location")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker_icon)));

            // Move the camera instantly  with a zoom of 15.
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(lat, lon), 15));

            // Zoom in, animating the camera.
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(12), 1000, null);
        }

        if (v.getParent() != null) {
            ((ViewGroup)v.getParent()).removeView(v);
        }
        return v;

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

//    public void onDestroyView() {
//        super.onDestroyView();
//        FragmentMa
//        FragmentManager fm = getActivity().getSupportFragmentManager();
//        Fragment fragment = (fm.findFragmentById(R.id.mapfragment));
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.remove(fragment);
//        ft.commit();
//    }

   }
