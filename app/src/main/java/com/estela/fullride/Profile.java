package com.estela.fullride;

import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Profile extends Fragment implements OnMapReadyCallback {

    private final FirebaseDatabase _database = FirebaseDatabase.getInstance("https://full-ride-59aee-default-rtdb.firebaseio.com/");
    private TextView  major, name;
    private Button sch, rou, logo, req, sendm;
    String profid;
    private ImageButton info;
    GoogleMap map;
    LatLng l = new LatLng(28.598797, -81.358315);
    private MarkerOptions place1, place2;
    private TextView  m, t, w, th, f, s, sn ;
    private double lat;
    private double longi;
    LatLng origin;
    LatLng dest;
    String place;
    //current and destination location objects

    protected LatLng start=null;
    protected LatLng end=null;

    //to get location permissions.
    private final static int LOCATION_REQUEST_CODE = 23;
    boolean locationPermission=false;

    //polyline object
    private List<Polyline> polylines=null;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override

        public void onMapReady(GoogleMap googleMap) {

            map = googleMap;

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.getUiSettings().isZoomGesturesEnabled();

            CameraUpdate c = CameraUpdateFactory.newLatLngZoom(l, 10);
            map.animateCamera(c);
            map.addMarker(place1);
//            direction();

        }
    };


    public Profile() {}

    public Profile(String profid) {

        this.profid = profid;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        major = getView().findViewById(R.id.pmajor);
        name = getView().findViewById(R.id.pname);
        sch = (Button) getView().findViewById(R.id.addsch);
        rou = (Button) getView().findViewById(R.id.addr);
        logo = (Button) getView().findViewById(R.id.logoutb);
        req = (Button) getView().findViewById(R.id.sendreqb);
        sendm = (Button) getView().findViewById(R.id.sendmb);
        info = (ImageButton) getView().findViewById(R.id.infob);

        m = (TextView) getActivity().findViewById(R.id.tm);
        t = (TextView) getActivity().findViewById(R.id.tt);
        w = (TextView) getActivity().findViewById(R.id.tw);
        th = (TextView) getActivity().findViewById(R.id.tth);
        f = (TextView) getActivity().findViewById(R.id.tf);
        s = (TextView) getActivity().findViewById(R.id.ts);
        sn = (TextView) getActivity().findViewById(R.id.tsn);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map4);

        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        if(profid !=  FirebaseAuth.getInstance().getCurrentUser().getUid())
        {
            logo.setVisibility(View.GONE);
            rou.setVisibility(View.GONE);
            sch.setVisibility(View.GONE);
        }
        else
        {            req.setVisibility(View.GONE);
                     sendm.setVisibility(View.GONE);
        }

        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(profid).child("_status").getValue(String.class).equals("driver")) {

                    major.setText(snapshot.child(profid).child("_major").getValue(String.class));
                    name.setText(snapshot.child(profid).child("_firstname").getValue(String.class) + " " + snapshot.child(profid).child("_lastname").getValue(String.class));
                    req.setVisibility(View.VISIBLE);

                    _database.getReference("driverloc").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            if (snapshot2.child(profid).child("_lat").exists()) {
                                place =  Double.toString(snapshot2.child(profid).child("_lat").getValue(double.class)) + ", " + Double.toString((snapshot2.child(profid).child("_long").getValue(double.class)));

                                lat = snapshot2.child(profid).child("_lat").getValue(double.class);
                                longi = snapshot2.child(profid).child("_long").getValue(double.class);
                                origin = new LatLng(lat, longi);
                                place2 = new MarkerOptions().position(origin);
                                map.addMarker(place2);

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            throw error.toException();
                        }

                    });
                }

                else if (snapshot.child(profid).child("_status").getValue(String.class).equals("rider")) {

                    major.setText(snapshot.child(profid).child("_major").getValue(String.class));
                    name.setText(snapshot.child(profid).child("_firstname").getValue(String.class) + " " + snapshot.child(profid).child("_lastname").getValue(String.class));
                    FirebaseDatabase.getInstance().getReference("riderloc").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot2) {
                            if (snapshot2.child(profid).child("_lat").exists()) {

                                lat = snapshot2.child(profid).child("_lat").getValue(double.class);
                                longi = snapshot2.child(profid).child("_long").getValue(double.class);
                                origin = new LatLng(lat, longi);
                                place2 = new MarkerOptions().position(origin);
                                map.addMarker(place2);
                                place =  Double.toString(snapshot2.child(profid).child("_lat").getValue(double.class)) + ", " + Double.toString((snapshot2.child(profid).child("_long").getValue(double.class)));


                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            throw error.toException();

                        }

                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }

        });

        FirebaseDatabase.getInstance().getReference("Schedules").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                m.setText(snapshot.child(profid).child("m").getValue(String.class));
                t.setText(snapshot.child(profid).child("tu").getValue(String.class));
                w.setText(snapshot.child(profid).child("w").getValue(String.class));
                th.setText(snapshot.child(profid).child("th").getValue(String.class));
                f.setText(snapshot.child(profid).child("f").getValue(String.class));
                s.setText(snapshot.child(profid).child("s").getValue(String.class));
                sn.setText(snapshot.child(profid).child("su").getValue(String.class));

            }

            @Override

            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        sch.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                SchedulePop s = new SchedulePop();

                s.show(getActivity().getSupportFragmentManager(), "ScheduleDialog");
            }

        });

        info.setVisibility(View.VISIBLE);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infopop su = new infopop();

                su.show(getActivity().getSupportFragmentManager(), "InfoDialog");
            }
        });

        rou.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), maproutepop.class);
                startActivity(i);

            }
        });

        logo.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                change();

                return;

            }

        });

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendRequestPopoup s = new SendRequestPopoup(profid);

                s.show(getActivity().getSupportFragmentManager(), "RequestDialog");
            }
        });

        sendm.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        ChatfragItem c = new ChatfragItem("", "",userID, profid);
                        _database.getReferenceFromUrl("https://full-ride-59aee-default-rtdb.firebaseio.com/").child("ChatfragItem").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                                .getUid()).setValue(c);

                        showselected(new ChatsFragment());

                    }

                    @Override

                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });


            }

        });



        dest = new LatLng(28.596273848615905, -81.30169704331314);

        place1 = new MarkerOptions().position(dest);

    }
    private void change(){

        Intent intent = new Intent(getActivity(), ChooseUser.class);
        startActivity(intent);

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