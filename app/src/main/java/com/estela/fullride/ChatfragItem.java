package com.estela.fullride;

import java.io.Serializable;

public class ChatfragItem implements Serializable {

    private String name, msg, senderid, receiverid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public ChatfragItem(String _name,String  _msg,String _senderid,String  _receiverid){
        name = _name;
        msg = _msg;
        senderid = _senderid;
        receiverid = _receiverid;
    }

}
