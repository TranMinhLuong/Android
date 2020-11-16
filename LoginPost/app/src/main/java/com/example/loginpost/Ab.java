package com.example.loginpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Ab extends AppCompatActivity {
    TextView txtnameuser;
    String SESSION_KEY = "SESSION_USER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ab);

        txtnameuser = findViewById(R.id.txttenuser);
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String name = sessionManagement.sharedPreferences.getString(SESSION_KEY, "");
        txtnameuser.setText(name);
    }
}
