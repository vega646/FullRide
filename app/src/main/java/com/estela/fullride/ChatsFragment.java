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

public class ChatsFragment extends Fragment {
    RecyclerView  unseen;
    RecyclerView.Adapter adapter;
    ArrayList<ChatfragItem> items ;
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


    public ChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        unseen = getView().findViewById(R.id.rvcahtitems);

        unseen.setLayoutManager(new LinearLayoutManager(this.getContext()));

        items = new ArrayList<>();


        adapter = new ChatFragAdapter(items, new ChatFragAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ChatfragItem ac) {

            }


        });

        unseen.setAdapter(adapter);

        createrequest();
    }
    private void createrequest(){
        DatabaseReference userefdata = FirebaseDatabase.getInstance().getReference().child("ChatfragItem");

        userefdata.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snap : snapshot.getChildren()) {



                        ChatfragItem evt = new ChatfragItem(snap.child("name").getValue(String.class),
                                snap.child("name").getValue(String.class),
                                snap.child("senderid").getValue(String.class),
                                snap.child("receiverid").getValue(String.class)
                        );

                    if(snap.child("senderid").getValue(String.class).equals(userID) || snap.child("receiverid").getValue(String.class).equals(userID) ){
                        items.add(evt);
                    }



                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });}
    }