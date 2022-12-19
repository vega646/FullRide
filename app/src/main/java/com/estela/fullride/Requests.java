package com.estela.fullride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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


public class Requests extends Fragment {

    RecyclerView  unseen, acted;
    RecyclerView.Adapter adapter, adapter2;
    ArrayList<Requestitem> requestitems ;
    ArrayList<RequestAccepted>requestitemsaccted;


    public Requests() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_requests, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        unseen = getView().findViewById(R.id.recunseen);

        unseen.setLayoutManager(new LinearLayoutManager(this.getContext()));

        requestitems = new ArrayList<>();


        adapter = new RequestAdapter(requestitems, new RequestAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Requestitem ev) {
                showselected(new Profile(ev.getRiderid()));
            }
        });

        unseen.setAdapter(adapter);

        createrequest();

        acted  =  getView().findViewById(R.id.reqacted);

        acted.setLayoutManager(new LinearLayoutManager(this.getContext()));

        requestitemsaccted = new ArrayList<>();


        adapter2 = new RequestAccepterAdapter(requestitemsaccted, new RequestAccepterAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(RequestAccepted ev) {

            }
        });

        acted.setAdapter(adapter2);


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
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });}



    private void createrequest(){
        DatabaseReference userefdata = FirebaseDatabase.getInstance().getReference().child("Requests");

        userefdata.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snap : snapshot.getChildren()) {

                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    String drivid = snap.child("driverid").getValue(String.class);

                    if (Objects.equals(drivid, userID)){

                        Requestitem evt = new Requestitem(snap.child("_name").getValue(String.class),
                            snap.child("_major").getValue(String.class),
                            snap.child("time").getValue(String.class),
                            snap.child("riderid").getValue(String.class),
                            snap.child("driverid").getValue(String.class),
                            false
                        );

                            requestitems.add(evt);


                    }
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });}


    private void showselected(Fragment f){

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cont, f)

                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                .commit();

    }
}