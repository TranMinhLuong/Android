package com.example.loginpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtuser,edtpass;
    private TextView txtten;
    private Button btnlogin;
    ModelLogin modelLogin = new ModelLogin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtuser.getText().toString().trim();
                String pass = edtpass.getText().toString().trim();
                loginas loginas = new loginas();
                loginas.execute(user,pass);
            }
        });
    }


    private void addControls() {
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        txtten = findViewById(R.id.txtten);
        btnlogin = findViewById(R.id.btnlogin);
    }

    class loginas extends TaskLogin{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                String json = s;
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    if (item.has("fullname")){
                        ModelLogin modelLogin = new ModelLogin();
                        modelLogin.setTen(item.getString("fullname"));
                        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                        sessionManagement.saveSession(modelLogin);
                        txtten.setText(item.getString("fullname"));
                        startActivity(new Intent(getApplicationContext(), Ab.class));
                    }else if(item.has("error")){
                        txtten.setText(item.getString("error"));
                    }
                    Log.d("av", txtten.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
    }
}
