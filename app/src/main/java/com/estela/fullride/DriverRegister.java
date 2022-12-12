package com.estela.fullride;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DriverRegister extends AppCompatActivity implements View.OnClickListener{


    private final FirebaseDatabase _database = FirebaseDatabase.getInstance("https://full-ride-59aee-default-rtdb.firebaseio.com/");
    //firebase Auth
    private FirebaseAuth _authentication;
    //information need it
    private EditText  _fullname, _lastname, _email, _password, _major ;
    private String _status;
    private DatabaseReference ref;
    //buttons
    private Button _registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_register);

        _authentication = FirebaseAuth.getInstance();
        _fullname = findViewById(R.id.register_firstname);
        _lastname = findViewById(R.id.register_lastname);
        _email = findViewById(R.id.register_email);
        _password = findViewById(R.id.register_password);
        _major =findViewById(R.id.major);
        _status = "driver";

        //buttons
        _registerBtn = findViewById(R.id.register_btn);
        ImageButton _back_Login = findViewById(R.id.register_back);

        _back_Login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.register_back) {

            startActivity(new Intent(this, MainActivity.class));

        }

        if (v.getId() == R.id.register_btn) {

            RegisterUser();

        }
    }


    private void RegisterUser() {
        String _FirstName = _fullname.getText().toString().trim();
        String _LastName = _lastname.getText().toString().trim();
        String _Email = _email.getText().toString().trim();
        String _Password = _password.getText().toString().trim();
        String _Major = _major.getText().toString().trim();


        if (_FirstName.isEmpty()) {
            _fullname.setError("Please enter your first name");
            _fullname.requestFocus();
            return;

        }
        if (_Major.isEmpty()) {
            _major.setError("Please enter your degree name");
            _major.requestFocus();
            return;

        }

        if (_LastName.isEmpty()) {
            _fullname.setError("Please enter your last name");
            _fullname.requestFocus();
            return;

        }
        if (_Email.isEmpty()) {
            _email.setError("Please enter your email address");
            _email.requestFocus();
            return;
        }

        if (_Password.isEmpty()) {
            _password.setError("Please enter a valid password");
            _password.requestFocus();
            return;

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(_Email).matches()) {
            _email.setError("You must enter a valid email");
            _email.requestFocus();
            return;
        }
        if (_Password.length() < 6) {

            _password.setError("Password must be a minimum of 8 characters");

            _password.requestFocus();
            return;
        }
        if (!HasCapitalLetters(_Password)) {

            _password.setError("Password must contain at least one capital letter");
            _password.requestFocus();

        }

        _authentication.createUserWithEmailAndPassword(_Email, _Password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        User_information users = new User_information(_FirstName, _LastName,  _Email, _Password, _Major, _status,userID);
                        _database.getReferenceFromUrl("https://full-ride-59aee-default-rtdb.firebaseio.com/").child("users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser())
                                        .getUid()).setValue(users)
                                .addOnCompleteListener
                                        (task1 -> {
                                            if (task1.isComplete()) {

                                                Toast.makeText(DriverRegister.this, "You're registered as a driver!", Toast.LENGTH_LONG).show();
                                            } else {

                                                Toast.makeText(DriverRegister.this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show();

                                            }
                                        });

                    } else {

                        Toast.makeText(DriverRegister.this, "Something went wrong. Try again.", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public boolean HasCapitalLetters(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
