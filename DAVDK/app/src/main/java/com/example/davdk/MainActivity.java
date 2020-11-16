package com.example.davdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {
    SeekBar speek,huong;
    TextView txtND,txtDA;
    int maxSpeek = 60;
    int minSpeek = (-1)*60;
    JSONObject data;
    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            socket = IO.socket("https://vdk.herokuapp.com/");
            socket.connect();
            Log.d("TAG", String.valueOf(socket.connected()));
        }catch (URISyntaxException ex){
            Log.e("LOI_Connect",ex.toString());
        }

        txtDA = findViewById(R.id.txtDA);
        txtND = findViewById(R.id.txtND);
        speek = findViewById(R.id.run);
        speek.setMax(maxSpeek-minSpeek  );
        speek.setProgress(0-minSpeek);
        huong = findViewById(R.id.quay);
        huong.setMax(150-30);
        huong.setProgress(90-30);
        data = new JSONObject();
        addEvents();
        socket.on("dht", dht);

    }

    public Emitter.Listener dht = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject doam = (JSONObject) args[0];
                    try {
                        if (doam.has("temperature")) {
                            txtND.setText(doam.getString("temperature")+" ºC");
                        }
                        if (doam.has("humidity")) {
                            txtDA.setText(doam.getString("humidity")+"%");
                        }
                    } catch (Exception ex) {

                    }
                }
            });
        }
    };

    private void addEvents() {
        speek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = (speek.getProgress()-60);
                if (value>0){
                    value = (value*10) + 400;
                }else {
                    value = (value*10) - 400;
                }
                try {
                    data.put("type", "car");
                    data.put("value", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("control", data);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                speek.setProgress(0-minSpeek);;
                try {
                    data.put("type", "car");
                    data.put("value", 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("control", data);
            }
        });

        huong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = huong.getProgress()+30;
                try {
                    data.put("type", "servo");
                    data.put("value", value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("control", data);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                huong.setProgress(60);
                try {
                    data.put("type", "servo");
                    data.put("value", huong.getProgress()+30);
                    JSONObject dht = new JSONObject();
                    dht.put("type", "dht");
                    dht.put("value", "");
                    socket.emit("control", dht);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("control", data);
            }
        });
    }


}