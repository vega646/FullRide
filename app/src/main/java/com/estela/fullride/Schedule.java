package com.estela.fullride;

import java.io.Serializable;

public class Schedule implements Serializable {

    private String m, tu, w, th, f ,s, su;

    public Schedule(String _m,String _tu,String  _w,String  _th, String _f,String  _s, String _su)
    {
        m = _m;
        tu = _tu;
        w = _w;
        th = _th;
        f = _f;
        s = _s;
        su = _su;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getTh() {
        return th;
    }

    public void setTh(String th) {
        this.th = th;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }




}
