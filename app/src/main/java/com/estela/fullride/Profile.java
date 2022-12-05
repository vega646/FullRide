package com.estela.fullride;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class Profile extends Fragment  {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//
    public Profile() {
        // Required empty public constructor
    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Profile.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Profile newInstance(String param1, String param2) {
//        Profile fragment = new Profile();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false);
//    }

    private TextView  major, name, m, t, w, th, f, s, sn ;
    private Button sch, rou;
    private CardView sche,rout ;
    private EditText m1, t1, w1, th1, f1, s1, sn1, m2, t2, w2, th2, f2, s2, sn2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        major = getView().findViewById(R.id.pmajor);
        name = getView().findViewById(R.id.pname);


        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                major.setText(snapshot.child(userID).child("_major").getValue(String.class));
                name.setText(snapshot.child(userID).child("_firstname").getValue(String.class) + " " + snapshot.child(userID).child("_lastname").getValue(String.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sch = (Button) getView().findViewById(R.id.addsch);
        rou = (Button) getView().findViewById(R.id.addr);

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
                RoutePop r = new RoutePop();

                r.show(getActivity().getSupportFragmentManager(), "RouteDialog");
            }
        });
    }
}