package com.estela.fullride;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseUser;

public class Markersm {

    double _lat;
    double _long;
    String creator;

    public Markersm(double _Lat, double _Long, String _Creator){
        _lat = _Lat;
        _long = _Long;
        creator = _Creator;
//        _map = _Map;
//        LatLng l = new LatLng(_Lat, _Long);
//        _Map.addMarker(new MarkerOptions().position(l));


    }

    public double get_lat() {
        return _lat;
    }

    public void set_lat(double _lat) {
        this._lat = _lat;
    }

    public double get_long() {
        return _long;
    }

    public void set_long(double _long) {
        this._long = _long;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


}
