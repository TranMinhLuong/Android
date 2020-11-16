package com.example.applicationeot.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.applicationeot.Models.BinhLuanMG;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TaskBinhLuan extends AsyncTask<String, Void, ArrayList<BinhLuanMG>> {
    @Override
    protected ArrayList<BinhLuanMG> doInBackground(String... strings) {
        ArrayList<BinhLuanMG> ds = new ArrayList<>();
        String idsp = strings[0];
        try {
            URL url = new URL("http://192.168.1.247/PHPCK/API/ItemProduct/"+idsp);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("Accept", "*/*");

            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            StringBuilder builder = new StringBuilder();
            while (line!=null){
                builder.append(line);
                line=br.readLine();
            }

            String json = builder.toString();

            JSONArray jsonArray = new JSONArray(json);
            for (int i=0; i<jsonArray.length(); i++){
                JSONObject item = jsonArray.getJSONObject(i);
                BinhLuanMG binhLuanMG = new BinhLuanMG();

                binhLuanMG.setTenkh(item.getString("tenkh"));
                binhLuanMG.setCreated(item.getString("created_at"));
                binhLuanMG.setComment(item.getString("comment"));
                binhLuanMG.setRating(item.getString("rating"));

                ds.add(binhLuanMG);
                Log.d("JSONSP", json);
            }
        }catch (Exception e){
            Log.e("Loi", e.toString());
        }
        return ds;
    }
}
