package com.estela.fullride;

import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.sql.Time;

public class Requestitem implements Serializable {

    private String _name;
    private String _major;
    private FirebaseUser u;
    private  String time;




    public Requestitem(String name, String major, String _time, FirebaseUser _u){
        _name = name;
        _major = major;
        time = _time;
        u = _u;
    }

    public String GetName(){
        return _name;
    }

    public String GetMajor(){
        return _major;
    }

    public String GetDate(){
        return time;
    }

    public FirebaseUser GetU() {return u;}

}