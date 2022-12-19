package com.estela.fullride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Barclass extends AppCompatActivity  {

    private BottomNavigationView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_maps);

        v = findViewById(R.id.bottomNavigationView);

        if (v != null)
        {
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            showselected(new Profile(userID));

            v.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    if (item.getItemId() == R.id.find){

                        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {

                            @Override

                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.child(userID).child("_status").getValue(String.class).equals("driver")) {

                                    showselected(new DriverMapsFragment());
                                }

                                else if (snapshot.child(userID).child("_status").getValue(String.class).equals("rider")) {
                                    showselected(new RiderMapsFragment());
                                }
                            }


                            @Override

                            public void onCancelled(@NonNull DatabaseError error) {

                            }

                        });

                    }

                    if (item.getItemId() == R.id.req){
                        showselected(new Requests());
                    }

                    if (item.getItemId() == R.id.rh){
                        showselected(new RideHistory());
                    }

                    if (item.getItemId() == R.id.profile){
                        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        showselected(new Profile(userID));
                    }

                    if (item.getItemId() == R.id.mes){
                        showselected(new ChatsFragment());
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

