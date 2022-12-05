package com.estela.fullride;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.estela.fullride.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class mapProfile extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private Place p;
    private PlaceSelectionListener pl;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    // Set the fields to specify which types of place data to
    // return after the user has made a selection.
    private LatLng wp = new LatLng(28.598797,-81.358315);
    private MapFragment mapFragment;

    // Start the autocomplete intent.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map2);
        setupAutoCompleteFragment();

//        if (ContextCompat.checkSelfPermission(mapProfile.this, Manifest.permission.ACCESS_FINE_LOCATION)
//        != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(mapProfile.this, new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION
//            },100);
//        }
//        SupportMapFragment m = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
//
//        m.getMapAsync(this);
//
//        String api = getString(R.string.API_KEY);
//
//        Places.initialize(getApplicationContext(), api, Locale.US);
//
//        PlacesClient p = Places.createClient(this);
//
//
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
//                .build(this);
//        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

    }

    private void setupAutoCompleteFragment() {
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        if (autocompleteFragment != null) {
            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    wp = place.getLatLng();
                    mapFragment.getMapAsync(mapProfile.this);
                }

                @Override
                public void onError(Status status) {
                    Log.e("Error", status.getStatusMessage());
                }
            });
        }
    }
//    private OnMapReadyCallback callback = new OnMapReadyCallback() {
//
//        /**
//         * Manipulates the map once available.
//         * This callback is triggered when the map is ready to be used.
//         * This is where we can add markers or lines, add listeners or move the camera.
//         * In this case, we just add a marker near Sydney, Australia.
//         * If Google Play services is not installed on the device, the user will be prompted to
//         * install it inside the SupportMapFragment. This method will only be triggered once the
//         * user has installed Google Play services and returned to the app.
//         */
//        @Override
//        public void onMapReady(GoogleMap googleMap) {
//            map = googleMap;
//
//            LatLng wp = new LatLng(28.598797,-81.358315);
//            map.addMarker(new MarkerOptions().position(wp).title("Marker in Winter Park"));
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(wp , 14));
//        }
//    };

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

            map.addMarker(new MarkerOptions().position(wp).title("Marker in Winter Park"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(wp , 14));
//
//
//        autocompleteFragment.setLocationBias(RectangularBounds.newInstance(
//
//                new LatLng(28.598797,-81.358315),
//                new LatLng(28.598797,-81.358315)
//        ));
//
//        autocompleteFragment.setCountries("US");
//        // Specify the types of place data to return.
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
//
//        // Set up a PlaceSelectionListener to handle the response.
//        pl = new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(@NonNull Place place) {
//                // TODO: Get info about the selected place.
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//            }
//
//
//            @Override
//            public void onError(@NonNull Status status) {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        };
//
//        autocompleteFragment.setOnPlaceSelectedListener(pl);
//
//    }
}}