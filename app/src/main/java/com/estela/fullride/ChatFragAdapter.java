package com.estela.fullride;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ChatFragAdapter extends RecyclerView.Adapter<ChatFragAdapter.viewH>  {

    private CardView c;
    private ArrayList<ChatfragItem> list2;
    final ChatFragAdapter.OnItemClickListener ac;
    private final FirebaseDatabase _database = FirebaseDatabase.getInstance("https://full-ride-59aee-default-rtdb.firebaseio.com/");

    public ChatFragAdapter(ArrayList<ChatfragItem> l, ChatFragAdapter.OnItemClickListener listen){
        list2 = l;
        this.ac = listen;
    }

    public interface OnItemClickListener
    {
        void OnItemClick(ChatfragItem ac);
    }

    @NonNull
    @Override
    public ChatFragAdapter.viewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatfrag_item,parent,false);
        ChatFragAdapter.viewH holderEvents_ = new ChatFragAdapter.viewH(v);
        return holderEvents_;
    }

    @Override
    public void onBindViewHolder(@NonNull viewH holder, int position) {

        FirebaseDatabase.getInstance().getReference().child("ChatfragItem")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            String receivid = snapshot.child("receiverid").getValue(String.class);
                            String sendid = snapshot.child("senderid").getValue(String.class);
                            String userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                            if (userID.equals(receivid)) {
                                if (sendid != null) {
                                    _database.getReference().child("users").child(sendid)
                                            .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot snap) {

                                                    String name = snap.child("_firstname").getValue(String.class) + " " + snap.child("_lastname").getValue(String.class);
                                                    holder.name.setText(name);

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    throw error.toException();
                                                }

                                            });
                                }
                            }
                            else if (userID.equals(sendid))
                            {
                                if (receivid != null) {
                                    _database.getReference().child("users").child(receivid)
                                            .addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot snap) {

                                                    String name = snap.child("_firstname").getValue(String.class) + " " + snap.child("_lastname").getValue(String.class);
                                                    holder.name.setText(name);

                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                    throw error.toException();

                                                }

                                            });
                                }
                            }
                            holder.msg.setText(list2.get(position).getMsg());
                            holder.bindData(list2.get(position));

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();

                    }

                });
    }



    @Override
    public int getItemCount() {
        return list2.size();
    }


    public class viewH extends RecyclerView.ViewHolder{

        TextView name,  msg;

        public viewH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.chatnamee);
            msg = itemView.findViewById(R.id.chatmsg);
            c = itemView.findViewById(R.id.chatcv);

            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), ChatScreen.class);
                    v.getContext().startActivity(intent);

                }
            });

        }
        public void bindData(ChatfragItem i) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ac.OnItemClick(i);
                }
            });
        }

    }
}

