package com.example.giuaki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnmonan;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnmonan = findViewById(R.id.btnmon);
        toolbar = findViewById(R.id.toolbartrangchu);
        setSupportActionBar(toolbar);
    }

    private void addEvents() {
        btnmonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CacMonAnActivity.class);
                startActivity(intent);
            }
        });
    }
}
