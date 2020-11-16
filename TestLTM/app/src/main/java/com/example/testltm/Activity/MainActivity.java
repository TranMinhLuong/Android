package com.example.testltm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testltm.Constants.Constants;
import com.example.testltm.R;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class MainActivity extends AppCompatActivity {
    private EditText edtid, edtname, edtroomid;
    private Button btncreate, btnjoin;
    private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSockets();
        addControls();
        addEvents();
    }

    private void addSockets() {
        try {
            socket = IO.socket(Constants.SERVER_URL);
            socket.connect();
            Log.d("TAG", String.valueOf(socket.connected()));
        }catch (Exception e){
            Log.e("LOI_Connect",e.toString());
        }
    }

    private void addEvents() {
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), VideoCall.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void addControls() {
        edtid = findViewById(R.id.edtid);
        edtname = findViewById(R.id.edtname);
        edtroomid = findViewById(R.id.edtroom);
        btncreate =  findViewById(R.id.btncreate);
        btnjoin = findViewById(R.id.btnjoin);
    }
}