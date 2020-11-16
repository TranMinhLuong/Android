package com.example.applicationeot.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationeot.R;


public class HeaderMenuActivity extends AppCompatActivity {
    TextView txtactivityuser;
    String SESSION_KEY = "SESSION_USER";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_menu);
        addControls();
    }

    private void addControls() {
        txtactivityuser = findViewById(R.id.activityusername);
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String name = sessionManagement.sharedPreferences.getString(SESSION_KEY, "");
        txtactivityuser.setText(name);
        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
        Log.d("ABC", txtactivityuser.toString());
    }
}
