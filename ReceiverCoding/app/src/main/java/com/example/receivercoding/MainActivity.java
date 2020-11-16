package com.example.receivercoding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btndn;

    BroadcastReceiver wifireceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo()!= null){
                btndn.setVisibility(btndn.VISIBLE);
                Toast.makeText(context, "Bạn vừa mở internet", Toast.LENGTH_LONG).show();
            }else {
                btndn.setVisibility(btndn.INVISIBLE);
                Toast.makeText(context, "Bạn vừa tắt internet", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifireceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wifireceiver!=null){ unregisterReceiver(wifireceiver); }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        btndn = findViewById(R.id.btndn);
    }

    private void addEvents() {

    }
}
