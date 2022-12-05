package com.estela.fullride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseUser  extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth _authent;
    private FirebaseUser _userdata;
    private Button d, r;
    @Override
    public void onClick(View v) {
        if (v.getId() == d.getId()) {
            startActivity(new Intent(this, DriverRegister.class));

        }
        if (v.getId() == r.getId()) {
            startActivity(new Intent(this, CustomerResgister.class));

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.driverrider);
        getSupportActionBar().hide();
        d = findViewById(R.id.drivb);
        r = findViewById(R.id.ridb);
        _authent = FirebaseAuth.getInstance();

        _userdata = FirebaseAuth.getInstance().getCurrentUser();


    }
    public void cust(){
        startActivity(new Intent(this, CustomerResgister.class));
    }

    public void driv(){            startActivity(new Intent(this, DriverRegister.class));
    }
    @Override
    public void onStart() {

        _userdata = _authent.getCurrentUser();
        if (_userdata != null) {
            Intent intent_ = new Intent(ChooseUser.this, Barclass.class);
            startActivity(intent_);
            this.finish();
        }
        super.onStart();

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driv();
            }
        });
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)     {
                cust();
            }
        });
    }
}
