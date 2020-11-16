package com.example.tygiahoidoai;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.TyGiaAdapter;
import com.example.models.TyGia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvtigia;
    ArrayList<TyGia> dstygia;
    TyGiaAdapter tyGiaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        lvtigia = findViewById(R.id.lvtygia);
        dstygia = new ArrayList<>();
        tyGiaAdapter = new TyGiaAdapter(MainActivity.this, R.layout.item_tygia, dstygia);
        lvtigia.setAdapter(tyGiaAdapter);

        TyGiaTask tyGiaTask = new TyGiaTask();
        tyGiaTask.execute();
    }

    class TyGiaTask extends AsyncTask<Void, Void, ArrayList<TyGia>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tyGiaAdapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<TyGia> tyGias) {
            super.onPostExecute(tyGias);
            tyGiaAdapter.clear();
            tyGiaAdapter.addAll(tyGias);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<TyGia> doInBackground(Void... params) {
            ArrayList<TyGia> ds = new ArrayList<>();
            try {
                URL url = new URL("https://www.dongabank.com.vn/exchange/export");
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
                Log.d("JSON","1236458");
                Log.d("JSON",json);
                json = json.replace("(", "");
                json = json.replace(")", "");

                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i=0; i<jsonArray.length(); i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    TyGia tyGia = new TyGia();
                    tyGia.setType(item.getString("type"));
                    if (item.has("imageurl")){
                        tyGia.setImgurl(item.getString("imageurl"));
                        url = new URL(tyGia.getImgurl());
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                        connection.setRequestProperty("Accept", "*/*");
                        Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                        tyGia.setBitmap(bitmap);
                    }
                    tyGia.setMuatienmat(item.getString("muatienmat"));
                    tyGia.setMuack(item.getString("muack"));
                    tyGia.setBantienmat(item.getString("bantienmat"));
                    tyGia.setBanck(item.getString("banck"));
                    ds.add(tyGia);
                }
            }catch (Exception e){
                Log.e("Loi", e.toString());
            }
            return ds;
        }
    }
}
