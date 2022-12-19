package com.estela.fullride;

public class ChatMessage {private String sender;
    private String date;
    private String message;


    private String receiverid;


    public ChatMessage(){};

    public ChatMessage(String sender, String message, String receiverid) {
        this.sender = sender;
        this.message = message;
        this.receiverid = receiverid;
    }




    public  String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

}
