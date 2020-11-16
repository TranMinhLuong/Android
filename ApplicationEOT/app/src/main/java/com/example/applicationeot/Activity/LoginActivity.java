package com.example.applicationeot.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationeot.Models.AccountMG;
import com.example.applicationeot.R;
import com.example.applicationeot.Task.TaskLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    Toolbar toolbarlogin;
    EditText edtuser, edtpass;
    CheckBox cbsave;
    Button btnlogin;
    TextView txtten;
    TextView txtregi,txtbackmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControls();
        addEvents();
    }

    private void addControls() {
        toolbarlogin = findViewById(R.id.toolbarlogin);
        setSupportActionBar(toolbarlogin);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        cbsave = findViewById(R.id.cbsave);
        btnlogin = findViewById(R.id.btnlogin);
        txtten = findViewById(R.id.txtten);
        txtten.setText("");
        txtregi = findViewById(R.id.txtregi);
        txtbackmain = findViewById(R.id.txtbackmain);
        String SESSION_KEY = "SESSION_USER";
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String name = sessionManagement.sharedPreferences.getString(SESSION_KEY, "");
        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
    }

    private void addEvents() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtuser.getText().toString().trim();
                String pass = edtpass.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()){
                    txtten.setText("Không hợp lệ");
                }else {
                    LoginAsy loginAsy = new LoginAsy();
                    loginAsy.execute(user,pass);
                }
            }
        });
        txtbackmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        txtregi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    class LoginAsy extends TaskLogin{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String json = s;
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i<jsonArray.length(); i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    if (item.has("fullname")){
                        AccountMG accountMG = new AccountMG();
                        accountMG.setName(item.getString("fullname"));
                        accountMG.setTaikhoan(item.getString("taikhoan"));
                        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
                        sessionManagement.SaveSession(accountMG);
                        txtten.setText("Login Success: "+item.getString("fullname"));
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else if(item.has("error")){
                        txtten.setText(item.getString("error"));
                    }
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
