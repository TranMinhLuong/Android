package com.example.applicationeot.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationeot.R;
import com.example.applicationeot.Task.TaskRegister;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtuserres, edtpassres, edtfullnameres, edtemailres;
    private Button btnres;
    private TextView txtlog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addControls();
        addEvents();
    }

    private void SignUp() {
        String username = edtuserres.getText().toString().trim();
        String password = edtpassres.getText().toString().trim();
        String fullname = edtfullnameres.getText().toString().trim();
        String email = edtemailres.getText().toString().trim();
        Register register = new Register();
        register.execute(fullname, username, password, email);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


    private void addControls() {
        edtuserres = findViewById(R.id.edtuserres);
        edtpassres = findViewById(R.id.edtpassres);
        edtfullnameres = findViewById(R.id.edtfullnameres);
        edtemailres = findViewById(R.id.edtemailres);
        btnres = findViewById(R.id.btnres);
        txtlog = findViewById(R.id.txtlog);
    }

    private void addEvents() {
        btnres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
        txtlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    class Register extends TaskRegister{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}