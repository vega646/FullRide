package com.estela.fullride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ChatScreen extends AppCompatActivity {


    private CardView c;
    private FloatingActionButton b;
    private EditText field;
    String rid;
    RecyclerView rv;
    Intent i;
    FirebaseUser user;
    RecyclerView.Adapter adapter;
    ArrayList<ChatMessage> chatitesms;
    DatabaseReference useref = FirebaseDatabase.getInstance().getReference().child("ChatfragItem");


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.send_message_chat);
        getSupportActionBar().hide();

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        useref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {

                    rid = snap.child("receiverid").getValue(String.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                 throw error.toException();
            }
        });

        rv = findViewById(R.id.rvcahtitems);
        b = findViewById(R.id.chatsendb);
        field = findViewById(R.id.chatfield);
        user = FirebaseAuth.getInstance().getCurrentUser();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = field.getText().toString();
                if ((!msg.equals("")))
                {
                    SendM(user.getUid(), rid, msg);
                }
                else
                {
                    Toast.makeText(ChatScreen.this, "Can't send an empty message", Toast.LENGTH_SHORT);
                }
                field.setText("");

            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));

        readmsgs(userID, rid);


    }

    private void readmsgs(String myid, String uid) {
        chatitesms = new ArrayList<>();

        DatabaseReference userefdata = FirebaseDatabase.getInstance().getReference().child("Chats");

        userefdata.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                chatitesms.clear();

                for (DataSnapshot snap : snapshot.getChildren()) {
                    ChatMessage m = snap.getValue(ChatMessage.class);
//                    if(m.getReceiverid().equals(myid)  ||  m.getSender().equals(myid))
//                    {
                        chatitesms.add(m);
//                    }

                    adapter = new ChatMessageAdapter(chatitesms);
                    rv.setAdapter(adapter);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    private  void  SendM(String sender, String receiver, String message)
    {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> map = new HashMap<>();
        map.put("sender", sender);
        map.put("receiver", receiver);
        map.put("message" , message);

        ref.child("Chats").push().setValue(map);

    }
}
