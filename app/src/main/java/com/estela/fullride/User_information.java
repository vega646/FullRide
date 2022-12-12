package com.estela.fullride;

public class User_information {

    public String _firstname;

    public String _lastname;

    public String _email;

    public String _password;

    public String _status;

    public String _major;

    public String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public String getMajor() {
        return _major;
    }

    public void setMajor(String major) {
        this._major = major;
    }

    public String get_firstname() {
        return _firstname;
    }

    public void set_firstname(String _firstname) {
        this._firstname = _firstname;
    }

    public String get_lastname() {
        return _lastname;
    }

    public void set_lastname(String _lastname) {
        this._lastname = _lastname;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public User_information(String _Firstname, String _Lastname, String _Email, String _Password, String Major, String Status, String ID) {

        _firstname = _Firstname;
        _lastname = _Lastname;
        _email = _Email;
        _password = _Password;
        _major = Major;
        _status = Status;
        _id = ID;
    }

}

