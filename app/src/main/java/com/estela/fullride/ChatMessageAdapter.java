package com.estela.fullride;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.viewHolderEvents> {
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private List<ChatMessage> list2;
    private Context context;
    private StorageReference pictureReference= FirebaseStorage.getInstance().getReference();

    public ChatMessageAdapter( List<ChatMessage> messenges ) {
        this.list2 = messenges;
    }


    @NonNull
    @Override
    public ChatMessageAdapter.viewHolderEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message,parent,false);
        ChatMessageAdapter.viewHolderEvents holderEvents_ = new ChatMessageAdapter.viewHolderEvents(v);
        return holderEvents_;

    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageAdapter.viewHolderEvents holder, int position) {

        ChatMessage c = list2.get(position);
        holder.msg.setText(c.getMessage());


        if (list2.get(position).getSender().equals(user.getUid()) ) {
            holder.card.setCardBackgroundColor(Color.parseColor("#F6A730"));
            holder.card.setRadius(50);

        }else {

            holder.card.setCardBackgroundColor(Color.LTGRAY);
            holder.card.setRadius(50);
        }
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public class viewHolderEvents extends RecyclerView.ViewHolder {

        TextView name, msg;
        CardView card;


        public viewHolderEvents(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name_msg);
            msg = itemView.findViewById(R.id.messange_msg);
            card = itemView.findViewById(R.id.cvmess);


        }
    }
}

