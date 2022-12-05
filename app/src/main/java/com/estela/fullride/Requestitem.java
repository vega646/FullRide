package com.estela.fullride;

import android.widget.Button;

import java.io.Serializable;
import java.sql.Time;

public class Requestitem implements Serializable {

    private String _name;
    private String _major;
    private  String time;


    public Requestitem(String name, String major, String _time){
        _name = name;
        _major = major;
        time = _time;
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


}