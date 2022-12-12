package com.estela.fullride;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    private TextView  major, name;
    private Button sch, rou, logo, req;
    String profid;
    private TextView  m, t, w, th, f, s, sn ;

    public Profile() {}


    public Profile(String profid) {

        this.profid = profid;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        major = getView().findViewById(R.id.pmajor);
        name = getView().findViewById(R.id.pname);
        sch = (Button) getView().findViewById(R.id.addsch);
        rou = (Button) getView().findViewById(R.id.addr);
        logo = (Button) getView().findViewById(R.id.logoutb);
        req = (Button) getView().findViewById(R.id.sendreqb);
        req.setVisibility(View.GONE);

        m = (TextView) getActivity().findViewById(R.id.tm);
        t = (TextView) getActivity().findViewById(R.id.tt);
        w = (TextView) getActivity().findViewById(R.id.tw);
        th = (TextView) getActivity().findViewById(R.id.tth);
        f = (TextView) getActivity().findViewById(R.id.tf);
        s = (TextView) getActivity().findViewById(R.id.ts);
        sn = (TextView) getActivity().findViewById(R.id.tsn);

        if(profid !=  FirebaseAuth.getInstance().getCurrentUser().getUid())
        {
            logo.setVisibility(View.GONE);
            rou.setVisibility(View.GONE);
            sch.setVisibility(View.GONE);
        }
//        else
//        {            req.setVisibility(View.GONE);
//        }

        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(profid).child("_status").getValue(String.class).equals("driver")) {

                    major.setText(snapshot.child(profid).child("_major").getValue(String.class));
                    name.setText(snapshot.child(profid).child("_firstname").getValue(String.class) + " " + snapshot.child(profid).child("_lastname").getValue(String.class));
                    req.setVisibility(View.VISIBLE);
                }

                else if (snapshot.child(profid).child("_status").getValue(String.class).equals("rider")) {

                    major.setText(snapshot.child(profid).child("_major").getValue(String.class));
                    name.setText(snapshot.child(profid).child("_firstname").getValue(String.class) + " " + snapshot.child(profid).child("_lastname").getValue(String.class));

                }
            }

            @Override

            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        FirebaseDatabase.getInstance().getReference("Schedules").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                m.setText(snapshot.child(profid).child("m").getValue(String.class));
                t.setText(snapshot.child(profid).child("tu").getValue(String.class));
                w.setText(snapshot.child(profid).child("w").getValue(String.class));
                th.setText(snapshot.child(profid).child("th").getValue(String.class));
                f.setText(snapshot.child(profid).child("f").getValue(String.class));
                s.setText(snapshot.child(profid).child("s").getValue(String.class));
                sn.setText(snapshot.child(profid).child("su").getValue(String.class));


            }

            @Override

            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        sch.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                SchedulePop s = new SchedulePop();

                s.show(getActivity().getSupportFragmentManager(), "ScheduleDialog");
            }

        });

        rou.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), maproutepop.class);
                startActivity(i);

            }
        });

        logo.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();

                change();

                return;

            }

        });

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendRequestPopoup s = new SendRequestPopoup(profid);

                s.show(getActivity().getSupportFragmentManager(), "RequestDialog");
            }
        });

    }
    private void change(){

        Intent intent = new Intent(getActivity(), ChooseUser.class);
        startActivity(intent);

    }
}