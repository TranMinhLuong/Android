package com.example.testltm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testltm.Constants.Constants;
import com.example.testltm.R;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class VideoCall extends AppCompatActivity {
    private Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        try {
            socket = IO.socket(Constants.SERVER_URL);
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        start();
    }

    private void start() {
        
    }


}