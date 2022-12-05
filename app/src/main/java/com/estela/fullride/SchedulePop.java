package com.estela.fullride;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchedulePop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedulePop extends AppCompatDialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText m1, t1, w1, th1, f1, s1, sn1, m2, t2, w2, th2, f2, s2, sn2;
    private TextView  m, t, w, th, f, s, sn ;
    private String nu;
    public SchedulePop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchedulePop.
     */
    // TODO: Rename and change types and number of parameters
    public static SchedulePop newInstance(String param1, String param2) {
        SchedulePop fragment = new SchedulePop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.popup, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        LayoutInflater cardIn = requireActivity().getLayoutInflater();
        View vr = inflater.inflate(R.layout.popup, null);

        m = (TextView) getActivity().findViewById(R.id.tm);
        t = (TextView) getActivity().findViewById(R.id.tt);
        w = (TextView) getActivity().findViewById(R.id.tw);
        th = (TextView) getActivity().findViewById(R.id.tth);
        f = (TextView) getActivity().findViewById(R.id.tf);
        s = (TextView) getActivity().findViewById(R.id.ts);
        sn = (TextView) getActivity().findViewById(R.id.tsn);

        m1 = (EditText) vr.findViewById(R.id.m1);
        t1 = (EditText) vr.findViewById(R.id.t1);
        w1 = (EditText) vr.findViewById(R.id.w1);
        th1 = (EditText) vr.findViewById(R.id.th1);
        f1 = (EditText) vr.findViewById(R.id.f1);
        s1 = (EditText) vr.findViewById(R.id.s1);
        sn1 = (EditText) vr.findViewById(R.id.sn1);

        m2 = (EditText) vr.findViewById(R.id.m2);
        t2 = (EditText) vr.findViewById(R.id.t2);
        w2 = (EditText) vr.findViewById(R.id.w2);
        th2 = (EditText) vr.findViewById(R.id.th2);
        f2 = (EditText) vr.findViewById(R.id.f2);
        s2 = (EditText) vr.findViewById(R.id.sn2);
        sn2 = (EditText) vr.findViewById(R.id.sn2);

//        nu = t1.getText().toString();
//        mParam1 = "";

        builder.setView(vr)

                .setNegativeButton("cancel",(dialog, which) -> {

                })
                .setPositiveButton("save", (dialog, which) -> {

                    if((TextUtils.isEmpty(m1.getText().toString())) || (TextUtils.isEmpty(m2.getText().toString()))){
                        m.setText("No Class");}
                    else{
                        m.setText( m1.getText() + " to " + m2.getText());
                    }

                    if((TextUtils.isEmpty(t1.getText().toString())) || (TextUtils.isEmpty(t2.getText().toString()))){
                        t.setText("No Class");}
                    else{
                        t.setText( t1.getText() + " to " + t2.getText());
                    }

                    if((TextUtils.isEmpty(w1.getText().toString())) || (TextUtils.isEmpty(w2.getText().toString()))){
                        w.setText("No Class");}
                    else{
                        w.setText( w1.getText() + " to " + w2.getText());
                    }

                    if((TextUtils.isEmpty(th1.getText().toString())) || (TextUtils.isEmpty(th2.getText().toString()))){
                        th.setText("No Class");}
                    else{
                        th.setText( th1.getText() + " to " + th2.getText());}

                    if((TextUtils.isEmpty(f1.getText().toString())) || (TextUtils.isEmpty(f2.getText().toString()))){
                        f.setText("No Class");}
                    else{

                        f.setText( f1.getText() + " to " + f2.getText());}
                    if((TextUtils.isEmpty(s1.getText().toString())) || (TextUtils.isEmpty(s2.getText().toString()))){
                        s.setText("No Class");}
                    else{
                        s.setText( s1.getText() + " to " + s2.getText());}

                    if((TextUtils.isEmpty(sn1.getText().toString())) || (TextUtils.isEmpty(sn2.getText().toString()))){
                        sn.setText("No Class");}
                    else{
                        sn.setText( sn1.getText() + " to " + sn2.getText());}
                });
        return builder.create();
    }
}