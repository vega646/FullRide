package com.estela.fullride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class RideHistory extends Fragment {


    RecyclerView rv;
    RecyclerView.Adapter adapter;
    ArrayList<RequestAccepted>requestitemsaccted;

    public RideHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride_history, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        rv  =  getView().findViewById(R.id.rvrideh);

        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        requestitemsaccted = new ArrayList<>();


        adapter = new RequestAccepterAdapter(requestitemsaccted, new RequestAccepterAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(RequestAccepted ev) {

            }
        });

        rv.setAdapter(adapter);


        createrequest2();
    }
    private void createrequest2() { DatabaseReference userefdata = FirebaseDatabase.getInstance().getReference().child("RequestsAccepted");

        userefdata.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snap : snapshot.getChildren()) {

                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    String drivid = snap.child("driverid").getValue(String.class);

                    if (Objects.equals(drivid, userID)){

                        RequestAccepted evt = new RequestAccepted(snap.child("_name").getValue(String.class),
                                snap.child("_major").getValue(String.class),
                                snap.child("time").getValue(String.class),
                                snap.child("riderid").getValue(String.class),
                                snap.child("driverid").getValue(String.class),
                                false
                        );

                        requestitemsaccted.add(evt);


                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });}

}