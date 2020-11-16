package com.example.backresultmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txta, txtb;
    Button btnxuli;
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnxuli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuliUCLN();
            }
        });
    }

    private void xuliUCLN() {
        Intent intent = new Intent(MainActivity.this, HandlingResultActivity.class);
        intent.putExtra("A", Integer.parseInt(txta.getText().toString()));
        intent.putExtra("B", Integer.parseInt(txtb.getText().toString()));
        startActivityForResult(intent,99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 33){
            int uscln = data.getIntExtra("USCLN", 1);
            txtkq.setText("Kết quả = "+uscln);
        }
    }

    private void addControls() {
        txta = findViewById(R.id.txta);
        txtb = findViewById(R.id.txtb);
        btnxuli = findViewById(R.id.btnxuly);
        txtkq = findViewById(R.id.txtkq);
    }
}
