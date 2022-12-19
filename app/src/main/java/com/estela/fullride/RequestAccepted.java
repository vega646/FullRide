package com.estela.fullride;

import java.io.Serializable;

public class RequestAccepted implements Serializable { private String _name;
    private String _major;
    private  String time;
    private String riderid;
    private String driverid;

    public Boolean getActedon() {
        return actedon;
    }

    public void setActedon(Boolean actedon) {
        this.actedon = actedon;
    }

    private Boolean actedon;

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public String get_major() {
        return _major;
    }

    public void set_major(String _major) {
        this._major = _major;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRiderid() {
        return riderid;
    }

    public void setRiderid(String riderid) {
        this.riderid = riderid;
    }

    public RequestAccepted(String name, String major, String _time, String _riderid, String _driverid, Boolean _actedon){
        _name = name;
        _major = major;
        time = _time;
        riderid = _riderid;
        driverid = _driverid;
        actedon = _actedon;
    }
}
