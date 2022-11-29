package com.estela.fullride;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
//    public Profile(){
//        // require a empty public constructor
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);

//        String user = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        String userID = user.getUid();
//
//
//        FirebaseDatabase.getInstance().getReference("users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                major.setText(snapshot.child(userID).child("_major").getValue(String.class));
//                name.setText(snapshot.child(userID).child("_firstname").getValue(String.class) + " " + snapshot.child(userID).child("_lastname").getValue(String.class));
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        sch = (Button) getView().findViewById(R.id.addsch);
        rou = (Button) getView().findViewById(R.id.addr);

        sche = (CardView) getView().findViewById(R.id.schcv);
        rout = (CardView) getView().findViewById(R.id.rcv);

        m = (TextView) getView().findViewById(R.id.tm);
        t = (TextView) getView().findViewById(R.id.tt);
        w = (TextView) getView().findViewById(R.id.tw);
        th = (TextView) getView().findViewById(R.id.tth);
        f = (TextView) getView().findViewById(R.id.tf);
        s = (TextView) getView().findViewById(R.id.ts);
        sn = (TextView) getView().findViewById(R.id.tsn);

        m1 = (EditText) getView().findViewById(R.id.m1);
        t1 = (EditText) getView().findViewById(R.id.t1);
        w1 = (EditText) getView().findViewById(R.id.w1);
        th1 = (EditText) getView().findViewById(R.id.th1);
        f1 = (EditText) getView().findViewById(R.id.f1);
        s1 = (EditText) getView().findViewById(R.id.s1);
        sn1 = (EditText) getView().findViewById(R.id.sn1);

        m2 = (EditText) getView().findViewById(R.id.m2);
        t2 = (EditText) getView().findViewById(R.id.t2);
        w2 = (EditText) getView().findViewById(R.id.w2);
        th2 = (EditText) getView().findViewById(R.id.th2);
        f2 = (EditText) getView().findViewById(R.id.f2);
        s2 = (EditText) getView().findViewById(R.id.sn2);
        sn2 = (EditText) getView().findViewById(R.id.sn2);

        if(m1 == null || m2 ==null){
            m.setText("No Class");}
        else{
        m.setText( m1.getText() + " to " + m2.getText());
        }

        if(t1 == null || t2 ==null){
            t.setText("No Class");}
        else{
            t.setText( t1.getText() + " to " + t2.getText());
        }

        if(w1 == null || w2 ==null){
            w.setText("No Class");}
        else{
            w.setText( w1.getText() + " to " + w2.getText());
        }

        if(th1 == null || th2 ==null){
            th.setText("No Class");}
        else{
        th.setText( th1.getText() + " to " + th2.getText());}

        if(f1== null || f2 ==null){
            f.setText("No Class");}
        else{

        f.setText( f1.getText() + " to " + f2.getText());}
        if(s1 == null || s2 ==null){
            s.setText("No Class");}
        else{
        s.setText( s1.getText() + " to " + s2.getText());}

        if(sn1 == null || sn2 ==null){
            sn.setText("No Class");}
        else{
        sn.setText( sn1.getText() + " to " + sn2.getText());}

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

            }
        });
    }
}