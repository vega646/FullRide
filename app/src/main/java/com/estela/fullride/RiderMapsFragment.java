package com.estela.fullride;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.estela.fullride.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class RiderMapsFragment extends Fragment implements OnMapReadyCallback {

    private ActivityMapsBinding binding;
    private GoogleMap map;
    LatLng l = new LatLng(28.598797, -81.358315);
    ArrayList<String> slist = new ArrayList<String>();

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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.rider_map_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map3);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


        FirebaseDatabase.getInstance().getReference().child("driverloc")
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
                    public void onCancelled(@NonNull DatabaseError error) {

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

