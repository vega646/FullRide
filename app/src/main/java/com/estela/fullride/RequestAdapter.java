package com.estela.fullride;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.viewH>{

    private CardView c;
    private ArrayList<Requestitem> list2;
    final RequestAdapter.OnItemClickListener ac;
    private int pos;

    public RequestAdapter(ArrayList<Requestitem> l, RequestAdapter.OnItemClickListener listen){
        list2 = l;
        this.ac = listen;
    }

    public interface OnItemClickListener
    {
        void OnItemClick(Requestitem ac);
    }

    @NonNull
    @Override
    public viewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item,parent,false);
        RequestAdapter.viewH holderEvents_ = new RequestAdapter.viewH(v);
        return holderEvents_;
    }

    @Override
    public void onBindViewHolder(@NonNull viewH holder, int position) {

        pos = position;
        FirebaseDatabase.getInstance().getReference().child("Requests")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            holder.date.setText(list2.get(position).getTime());
                            holder.name.setText(list2.get(position).get_name());
                            holder.major.setText(list2.get(position).get_major());
                            holder.bindData(list2.get(position));

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
        Button ace, deny;

        public viewH(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.actname);
            date = itemView.findViewById(R.id.actdate);
            major = itemView.findViewById(R.id.amajor);
            ace = itemView.findViewById(R.id.bacc);
            deny = itemView.findViewById(R.id.bcanc);
            c = itemView.findViewById(R.id.cvactiv);

            ace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    list2.get(pos).setActedon(true);

                }
            });

            deny.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list2.remove(list2.get(pos-1));
                }
            });
        }
        public void bindData(Requestitem i) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ac.OnItemClick(i);
                }
            });
        }

    }
}
