package com.example.backresultmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlingResultActivity extends AppCompatActivity {

    TextView txtnhan;
    Button btntinh;
    Intent intent;
    int a,b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handling_result);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btntinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulitinh();
            }
        });
    }

    private void xulitinh() {
        int min = Math.min(a,b);
        int uscln = 1;
        for (int i=min; i>=1; i--){
            if (a%i==0 && b%i==0){
                uscln = i;
                break;
            }
        }
        intent.putExtra("USCLN", uscln);
        setResult(33,intent);
        finish();
    }

    private void addControls() {
        txtnhan = findViewById(R.id.txtnhan);
        btntinh = findViewById(R.id.btntinh);

        intent = getIntent();
        a = intent.getIntExtra("A", -1);
        b = intent.getIntExtra("B", -1);
        txtnhan.setText("a = "+a+" b = "+b);

    }

}
