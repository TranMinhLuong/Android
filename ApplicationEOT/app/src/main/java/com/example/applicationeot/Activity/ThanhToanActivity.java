package com.example.applicationeot.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationeot.Adapter.GioHangAdapter;
import com.example.applicationeot.Adapter.ThanhToanAdapter;
import com.example.applicationeot.Models.GioHang;
import com.example.applicationeot.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

public class ThanhToanActivity extends AppCompatActivity {
    private EditText edttenkhtt, edtgendertt, edtcmttt, edtemailtt, edtphonett, edtaddtt;
    private RecyclerView recyclergiohangtt;
    static TextView txttotaltt;
    private Button btndathang;
    private Toolbar toolbarthanhtoan;
    private ThanhToanAdapter thanhToanAdapter;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        addControls();
        addEvents();
        Total();
    }

    public static void Total() {
        int total = 0;
        for (int i = 0; i<MainActivity.dsgiohang.size(); i++){
            total += Integer.parseInt(MainActivity.dsgiohang.get(i).getGiaspgh());
        }
        txttotaltt.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(total) + " VNĐ");
    }

    private void addControls() {
        String SESSION_KEY = "SESSION_USER";
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String name = sessionManagement.sharedPreferences.getString(SESSION_KEY, "");
        toolbarthanhtoan = findViewById(R.id.toolbarthanhtoan);
        setSupportActionBar(toolbarthanhtoan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edttenkhtt =  findViewById(R.id.edttenkhtt);
        edttenkhtt.setText(name);
        edtgendertt =  findViewById(R.id.edtgendertt);
        edtcmttt = findViewById(R.id.edtcmttt);
        edtemailtt = findViewById(R.id.edtemailtt);
        edtphonett =  findViewById(R.id.edtphonett);
        edtaddtt = findViewById(R.id.edtaddtt);
        txttotaltt = findViewById(R.id.txttotaltt);
        btndathang = findViewById(R.id.btndathang);
        recyclergiohangtt = findViewById(R.id.recyclergiohangtt);
        thanhToanAdapter = new ThanhToanAdapter(getApplicationContext(), MainActivity.dsgiohang);
        recyclergiohangtt.setAdapter(thanhToanAdapter);
        recyclergiohangtt.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addEvents() {
        toolbarthanhtoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatHang();
            }
        });
    }

    private void DatHang() {
        String tenkh = edttenkhtt.getText().toString().trim();
        Log.d("DatHang", tenkh);
        String gender = edtgendertt.getText().toString().trim();
        Log.d("DatHang", gender);
        String socm = edtcmttt.getText().toString().trim();
        Log.d("DatHang", socm);
        String email = edtemailtt.getText().toString().trim();
        Log.d("DatHang", email);
        String sdt = edtphonett.getText().toString().trim();
        Log.d("DatHang", sdt);
        String diachi = edtaddtt.getText().toString().trim();
        Log.d("DatHang", diachi);
        int total = 0;
        for (int i = 0; i<MainActivity.dsgiohang.size(); i++){
            total += Integer.parseInt(MainActivity.dsgiohang.get(i).getGiaspgh());
        }
        Log.d("DatHang", String.valueOf(total));

        JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < MainActivity.dsgiohang.size(); i++){
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("idsp", MainActivity.dsgiohang.get(i).getIdsp());
                    Log.d("DatHang", String.valueOf(MainActivity.dsgiohang.get(i).getIdsp()));
                    jsonObject.put("tensp", MainActivity.dsgiohang.get(i).getTenspgh());
                    Log.d("DatHang", String.valueOf(MainActivity.dsgiohang.get(i).getTenspgh()));
                    jsonObject.put("soluong", MainActivity.dsgiohang.get(i).getSoluongsp());
                    Log.d("DatHang", String.valueOf(MainActivity.dsgiohang.get(i).getSoluongsp()));
                }catch (JSONException e){
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }
            String json = jsonArray.toString();
            Log.d("JSON_Login",json);
        ThanhToan thanhToan = new ThanhToan();
        thanhToan.execute(tenkh, gender, socm, email, sdt, diachi, String.valueOf(total), json);
        Toast.makeText(this, "Pay Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        SharedPreferences preferences = getSharedPreferences("User", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.clear();
//        editor.commit();
        MainActivity.dsgiohang.clear();
    }

    class ThanhToan extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("http://192.168.1.247/PHPCK/API/ThanhToan");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                Uri.Builder builders = new Uri.Builder()
                        .appendQueryParameter("tenkh", strings[0])
                        .appendQueryParameter("gt", strings[1])
                        .appendQueryParameter("socm", strings[2])
                        .appendQueryParameter("email", strings[3])
                        .appendQueryParameter("sdt", strings[4])
                        .appendQueryParameter("diachi", strings[5])
                        .appendQueryParameter("total", strings[6])
                        .appendQueryParameter("json", strings[7]);
                String query = builders.build().getEncodedQuery();
                bufferedWriter.write(query);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                connection.connect();
                int response_code = connection.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK)
                {
                    InputStreamReader isr=new InputStreamReader(connection.getInputStream(),"UTF-8");
                    BufferedReader br=new BufferedReader(isr);
                    String line=br.readLine();
                    StringBuilder builder=new StringBuilder();
                    while (line!=null)
                    {
                        builder.append(line);
                        line=br.readLine();
                    }
                    String json=builder.toString();

                    br.close();
                    isr.close();
                    connection.disconnect();
                    Log.d("ABC", json);
                    return json;
                }
            }catch (Exception e){
                Log.e("Loi", e.toString());
            }
            return null;
        }
    }
}
