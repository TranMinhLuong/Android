package com.example.loginpost;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TaskLogin extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder = new StringBuilder();
        String user = strings[0];
        String pass = strings[1];
        try {
            URL url = new URL("http://172.20.10.3/PHPCK/API/Login/"+user+"/"+pass);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("Accept", "*/*");

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();

            while (line!=null){
                builder.append(line);
                line=br.readLine();
            }

            String json = builder.toString();
            JSONArray jsonArray = new JSONArray(json);
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);
                 ModelLogin modelLogin = new ModelLogin();
                item.put("taikhoan", user);
                item.put("matkhau", pass);
                modelLogin.setTen(item.getString("fullname"));
            }
        }catch (Exception e){
            Log.e("Loi", e.toString());
        }
        return builder.toString();
    }
}
