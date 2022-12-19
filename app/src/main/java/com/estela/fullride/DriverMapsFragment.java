package com.estela.fullride;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class DriverMapsFragment extends Fragment implements OnMapReadyCallback{

        private GoogleMap map;

    LatLng l = new LatLng(28.598797, -81.358315);
    ArrayList<String> slist = new ArrayList<String>();
private  int count;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override

        public void onMapReady(GoogleMap googleMap) {

            map = googleMap;

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.getUiSettings().isZoomGesturesEnabled();

            CameraUpdate c = CameraUpdateFactory.newLatLngZoom(l, 10);
            map.animateCamera(c);


        }
    };

    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.driver_map_fragment, container, false);
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map3);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if(sharedPreferences.getBoolean("IS_FIRST_TIME", true)) {
            infopopmap su = new infopopmap();

            su.show(getActivity().getSupportFragmentManager(), "InfoMapDialog");
            sharedPreferences.edit().putBoolean("IS_FIRST_TIME", false).apply();
        }


        FirebaseDatabase.getInstance().getReference().child("riderloc")

                .addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            double ll = snapshot.child("_lat").getValue(double.class);
                            double ll2 = snapshot.child("_long").getValue(double.class);
                            String l =  snapshot.child("creator").getValue(String.class);

                            slist.add(l);

                            LatLng lng = new LatLng(ll, ll2);


                            for (int i = 0; i < slist.size(); i++) {
                                int finalI = i;
                                Marker m = map.addMarker(new MarkerOptions()
                                        .position(lng));
                                int finalI1 = i;
                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                 @Override
                                    public boolean onMarkerClick(@NonNull Marker marker) {

                                        showselected(new Profile(slist.get(finalI1)));
                                        return false;
                                     }
                                });

                            }
                        }
                    }
                    @Override

                    public void onCancelled(DatabaseError databaseError) {
                    }

                });

    }


    @Override

    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;

    }
    private void showselected(Fragment f){

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont, f)

                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                .commit();

    }
}