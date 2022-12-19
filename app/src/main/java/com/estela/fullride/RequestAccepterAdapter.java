package com.estela.fullride;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class RequestAccepterAdapter extends RecyclerView.Adapter<RequestAccepterAdapter.viewH>{


    private ArrayList<RequestAccepted> list2;
    final RequestAccepterAdapter.OnItemClickListener ac;
    private int pos;
    ImageButton del;
    private final FirebaseDatabase _database = FirebaseDatabase.getInstance("https://full-ride-59aee-default-rtdb.firebaseio.com/");


    public RequestAccepterAdapter(ArrayList<RequestAccepted> l, RequestAccepterAdapter.OnItemClickListener listen){
        list2 = l;
        this.ac = listen;
    }

    public interface OnItemClickListener
    {
        void OnItemClick(RequestAccepted ac);
    }

    @NonNull
    @Override
    public RequestAccepterAdapter.viewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_accepted,parent,false);
        RequestAccepterAdapter.viewH holderEvents_ = new RequestAccepterAdapter.viewH(v);

        return holderEvents_;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestAccepterAdapter.viewH holder, int position) {

        FirebaseDatabase.getInstance().getReference().child("RequestsAccepted")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            holder.date.setText(list2.get(position).getTime());
                            holder.name.setText(list2.get(position).get_name());
                            holder.major.setText(list2.get(position).get_major());
                            holder.bindData(list2.get(position));
                            pos = position;

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }


    public class viewH extends RecyclerView.ViewHolder{

        TextView name, date, major;

        public viewH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.acceptedname);
            date = itemView.findViewById(R.id.accepteddate);
            major = itemView.findViewById(R.id.accceptedmajor);
            del = itemView.findViewById(R.id.deletereq);

            String rideridd =  list2.get(pos).getDriverid();

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseDatabase.getInstance().getReference("RequestsAccepted").child(rideridd).addValueEventListener(new ValueEventListener() {

                        @Override

                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            snapshot.getRef().removeValue();

                        }

                        @Override

                        public void onCancelled(@NonNull DatabaseError error) {

                        }

                    });
                }
            });

        }

        public void bindData(RequestAccepted i) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ac.OnItemClick(i);
                }
            });
        }

    }

}
