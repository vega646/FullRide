package com.estela.fullride;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
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
        private List<Profile> list;
    private Map<String, String> mMarkerMap = new HashMap<>();
    private  List<Profile> proflist;
    private MarkerOptions marker;
    private Vector<Profile> mprofiles;
    LatLng l = new LatLng(28.598797, -81.358315);
    ArrayList<String> slist = new ArrayList<String>();


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
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

        Map<String, String> mMarkerMap = new HashMap<>();

        FirebaseDatabase.getInstance().getReference().child("_riderloc")

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