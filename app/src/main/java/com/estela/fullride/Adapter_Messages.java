package com.estela.fullride;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class Adapter_Messages extends RecyclerView.Adapter<Adapter_Messages.viewHolderEvents> {
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private List<Messages> messenges;
    private Context context;
    private StorageReference pictureReference= FirebaseStorage.getInstance().getReference();
//    private DatabaseReference userref = FirebaseDatabase.getInstance().getReference().child(Messages.GetClickedEvent()).child("Messages");

    public Adapter_Messages(Context context, List<Messages> messenges ) {
        this.messenges = messenges;
        this.context = context;
    }


    @NonNull
    @Override
    public Adapter_Messages.viewHolderEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item,parent,false);

        return new viewHolderEvents(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Messages.viewHolderEvents holder, int position) {

        holder.name.setText(messenges.get(position).getSender());
        holder.date.setText(messenges.get(position).getDate());
        holder.messenger.setText((messenges.get(position).getMessage()));

        if (messenges.get(position).getSender().contains(user.getDisplayName())) {
            holder.card.setCardBackgroundColor(Color.GREEN);
            holder.card.setRadius(50);

        }else {

            holder.card.setCardBackgroundColor(Color.CYAN);
            holder.card.setRadius(50);
        }
    }

    @Override
    public int getItemCount() {
        return messenges.size();
    }

    public class viewHolderEvents extends RecyclerView.ViewHolder {

        TextView name,date,messenger;
        CardView card;
        ImageView profile;


        public viewHolderEvents(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name_msg);
            date  = itemView.findViewById(R.id.message_date);
            messenger = itemView.findViewById(R.id.messange_msg);
            card = itemView.findViewById(R.id.Category_events);



        }
    }
}

