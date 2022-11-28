package com.estela.fullride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.estela.fullride.databinding.ActivityMapsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Barclass extends AppCompatActivity {

    private BottomNavigationView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        v = findViewById(R.id.bottomNavigationView);

        if (v != null)
        {
            v.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    if (item.getItemId() == R.id.find){
                        showselected(new MapsFragment());
                    }

                    if (item.getItemId() == R.id.req){
                        showselected(new Requests());
                    }

                    if (item.getItemId() == R.id.rh){
                        showselected(new RideHistory());
                    }
                    if (item.getItemId() == R.id.profile){
                        showselected(new Profile());
                    }
                    if (item.getItemId() == R.id.mes){
                        showselected(new Messages());
                    }
                    return true;
                }
            });
        }

    }
    private void showselected(Fragment f){
        getSupportFragmentManager().beginTransaction().replace(R.id.cont, f)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }
}

