package com.example.mycontactapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtphone, txtmess;
    Button btnquayso, btngoiluon, btnnhantin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnquayso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyQuaySo();
            }
        });

        btngoiluon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyGoiLuon();
            }
        });

        btnnhantin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyNhanTin();
            }
        });
    }

    private void xulyNhanTin() {
        final SmsManager smsManager = SmsManager.getDefault();
        Intent msgsent = new Intent("ACTION_MSG_SENT");
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, msgsent, 0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String sms = "Gửi tin nhắn thành công";
                if (result != Activity.RESULT_OK){
                    sms = "Gửi tin nhắn thất bại";
                }
                Toast.makeText(MainActivity.this, sms, Toast.LENGTH_LONG).show();

            }
        }, new IntentFilter("ACTION_MSG_SENT"));
        smsManager.sendTextMessage(txtphone.getText().toString(), null, txtmess.getText()+"", pendingIntent,  null);
    }

    private void xulyGoiLuon() {
        Uri uri = Uri.parse("Tel: " + txtphone.getText().toString());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
             startActivity(intent);
    }

    private void xulyQuaySo() {
        Uri uri = Uri.parse("Tel: "+txtphone.getText().toString());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        startActivity(intent);
    }

    private void addControls() {
        txtphone = findViewById(R.id.txtphone);
        txtmess = findViewById(R.id.txtmess);
        btnquayso = findViewById(R.id.btnquayso);
        btngoiluon = findViewById(R.id.btngoiluon);
        btnnhantin = findViewById(R.id.btnnhantin);
    }
}
