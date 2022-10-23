package com.estela.fullride;

public class User_information {

    public String _firstname;
    public String _lastname;
    public String _email;
    public String _password;
    public String _uri;
    public String _major;

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



    public String get_uri() {
        return _uri;
    }

    public void set_uri(String _uri) {
        this._uri = _uri;
    }

    public User_information(String _Firstname, String _Lastname, String _Email, String _Password, String Uri, String Major) {

        _firstname = _Firstname;
        _lastname = _Lastname;
        _email = _Email;
        _password = _Password;
        _uri = Uri;
        _major = Major;
    }
}