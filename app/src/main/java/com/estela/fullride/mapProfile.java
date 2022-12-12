package com.estela.fullride;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class mapProfile extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap googleMap;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    private LocationManager locationManager;
    private final FirebaseDatabase _database = FirebaseDatabase.getInstance("https://full-ride-59aee-default-rtdb.firebaseio.com/");
    private String providr;
    private Marker marker;
    EditText input;
    ImageView search;
    private  List<Profile> proflist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popr);
        search = findViewById(R.id.searcrh);
        input = findViewById(R.id.input);
        proflist = new ArrayList<>();
        checkLocationPermission();
        locationManager  = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        providr = locationManager.getBestProvider(new Criteria(), false);

        SupportMapFragment m = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.container2, m).commit();
        if (m != null) {
        m.getMapAsync(this);}

        search.setOnClickListener(v -> {
            if (input != null) {
                String loc = input.getText().toString();
                if (loc == null) {
                    Toast.makeText(mapProfile.this, "Type any location name", Toast.LENGTH_SHORT).show();
                } else {
                    Geocoder g = new Geocoder(mapProfile.this, Locale.getDefault());
                    try {
                        List<Address> list = g.getFromLocationName(loc, 1);
                        if (list.size() > 0) {
                            LatLng lat = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());
                            MarkerOptions m1 = new MarkerOptions();
                            m1.title("Search Location");
                            m1.position(lat);
                            googleMap.addMarker(m1);
                            CameraUpdate c = CameraUpdateFactory.newLatLngZoom(lat, 5);
                            googleMap.animateCamera(c);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });




    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(mapProfile.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        locationManager.requestLocationUpdates(providr, 0, 0, this);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
            this.googleMap = googleMap;
            LatLng l = new LatLng(28.598797, -81.358315);
            MarkerOptions m = new MarkerOptions();
            m.title("My position");
            m.position(l);


            googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(@NonNull LatLng latLng) {
                    Log.d(TAG, "onMapLongClick:" + latLng.toString());

                    googleMap.addMarker(new MarkerOptions().position(latLng));
                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(@NonNull Marker marker) {
                            marker.setVisible(false);
                            return false;
                        }
                    });

                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    FirebaseDatabase.getInstance("https://full-ride-59aee-default-rtdb.firebaseio.com/").getReference("users").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            List<Markersm> listm;
                            listm = new ArrayList<>();
                            Markersm merk = new Markersm(latLng.latitude, latLng.longitude, userID);


                                if (snapshot.child(userID).child("_status").getValue(String.class).equals("driver")) {
                                    _database.getReferenceFromUrl("https://full-ride-59aee-default-rtdb.firebaseio.com/").child("_driverloc").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                                                    .getUid()).setValue(merk)

                                            .addOnCompleteListener

                                                    (task1 -> {

                                                        if (task1.isComplete()) {

                                                            Toast.makeText(mapProfile.this, "Your driver starting point has been set", Toast.LENGTH_LONG).show();

                                                        } else {

                                                            Toast.makeText(mapProfile.this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show();

                                                        }
                                                    });
                                }

                                else if (snapshot.child(userID).child("_status").getValue(String.class).equals("rider")) {
                                    _database.getReferenceFromUrl("https://full-ride-59aee-default-rtdb.firebaseio.com/").child("_riderloc").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                                                    .getUid()).setValue(merk)
                                            .addOnCompleteListener
                                                    (task1 -> {
                                                        if (task1.isComplete()) {

                                                            Toast.makeText(mapProfile.this, "Your driver starting point has been set", Toast.LENGTH_LONG).show();
                                                        } else {

                                                            Toast.makeText(mapProfile.this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show();

                                                        }
                                                    });
                                }
                            }


                            @Override
                            public void onCancelled (@NonNull DatabaseError error){

                            }

                    });
                }
            });

         CameraUpdate c = CameraUpdateFactory.newLatLngZoom(l, 10);
            this.googleMap.animateCamera(c);

            googleMap.getUiSettings().setZoomControlsEnabled(true);
//            googleMap.getUiSettings().setCompassEnabled(true);
//            googleMap.getUiSettings().setScrollGesturesEnabled(true);
            googleMap.getUiSettings().isZoomGesturesEnabled();
//            googleMap.getUiSettings().setRotateGesturesEnabled(false);
//            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

}


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {



    }
}