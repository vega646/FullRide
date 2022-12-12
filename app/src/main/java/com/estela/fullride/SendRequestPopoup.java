package com.estela.fullride;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SendRequestPopoup  extends AppCompatDialogFragment {

    private EditText date;
    private String name, major, reqid, n2, m2;
    private final FirebaseDatabase _database = FirebaseDatabase.getInstance("https://full-ride-59aee-default-rtdb.firebaseio.com/");
    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public SendRequestPopoup (String _reqid) {
    reqid = _reqid;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                n2 = (snapshot.child(userID).child("_firstname").getValue(String.class) + " " + snapshot.child(userID).child("_lastname").getValue(String.class));
                m2 = (snapshot.child(userID).child("_major").getValue(String.class));
            }

            @Override

            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        return inflater.inflate(R.layout.request_popup, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        LayoutInflater cardIn = requireActivity().getLayoutInflater();
        View vr = inflater.inflate(R.layout.request_popup, null);
        date = (EditText) vr.findViewById(R.id.reqdatttext);


        builder.setView(vr)

                .setNegativeButton("cancel",(dialog, which) -> {

                })
                .setPositiveButton("save", (dialog, which) -> {
                    if((TextUtils.isEmpty(date.getText().toString())) ){
                    }
                    else{

                        name = n2;
                        major = m2;
                        Requestitem r = new Requestitem(name, major, date.getText().toString(), userID, reqid, false);
                        _database.getReferenceFromUrl("https://full-ride-59aee-default-rtdb.firebaseio.com/").child("Requests").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                                .getUid()).setValue(r);
                    }
                });
        return builder.create();
    }
}
