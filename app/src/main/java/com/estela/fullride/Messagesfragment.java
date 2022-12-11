package com.estela.fullride;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Messagesfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Messagesfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Messages> messenges_List;
    Adapter_Messages adaptor_m ;
    RecyclerView reciclemsg;
    private EditText mess;
    private ScrollView sv;
    FloatingActionButton send_btn;
    LinearLayoutManager llman_;
    FirebaseUser user;
    FirebaseAuth Auth_;
    private static String ClickedEvent;
    private Messages m;
    private DatabaseReference refMessanging;
    private static String uid;

//    public void setClickedEvent(String e) {
//        this.e = ClickedEvent;
//    }
    public static String  GetClickedEvent(){

        return ClickedEvent;
    }
    public Messagesfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Messagesfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Messagesfragment newInstance(String param1, String param2) {
        Messagesfragment fragment = new Messagesfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Auth_ = FirebaseAuth.getInstance();
        user = Auth_.getCurrentUser();

        send_btn = getView().findViewById(R.id.sent_input);
        refMessanging =  FirebaseDatabase.getInstance().getReference();
        mess = getView().findViewById(R.id.Mensage_in);
        reciclemsg = getView().findViewById(R.id.recicle_mesg);
        messenges_List=new ArrayList<>();
        sv = getView().findViewById(R.id.scrollView2);
//        adaptor_m = new Adapter_Messages(this,messenges_List);
//        llman_=(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
//
//        reciclemsg.setLayoutManager(llman_);
//        reciclemsg.setAdapter(adaptor_m);
//
//        send_btn.setOnClickListener(v -> click());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }
}