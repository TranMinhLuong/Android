package com.example.Task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.Models.SanPhamMG;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TaskSanPhamMG extends AsyncTask<Void, Void, ArrayList<SanPhamMG>> {
    @Override
    protected ArrayList<SanPhamMG> doInBackground(Void... voids) {
        ArrayList<SanPhamMG> ds = new ArrayList<>();
        try {
            URL url = new URL("http://172.20.10.3/PHPCK/API/Show/sanpham");
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
                SanPhamMG sanPhamMG = new SanPhamMG();
                if (item.has("hinhanh")){
                    sanPhamMG.setUlrimage(item.getString("hinhanh"));
                    url = new URL("http://172.20.10.3/PHPCK/public/img/"+sanPhamMG.getUlrimage());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");
                    connection.setRequestProperty("Accept", "*/*");
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());

                    sanPhamMG.setImghinhsp(bitmap);
                }
                sanPhamMG.setId(item.getInt("idsp"));
                sanPhamMG.setTensp(item.getString("tensp"));
                sanPhamMG.setGiasp(item.getString("gia"));
                sanPhamMG.setKhuyenmai(item.getInt("km"));
                sanPhamMG.setIddm(item.getInt("iddm"));
                sanPhamMG.setNoidung(item.getString("noidung"));
                sanPhamMG.setNhacungcap(item.getString("nhacungcap"));

                ds.add(sanPhamMG);
                Log.d("JSONSP", json);
            }
        }catch (Exception e){
            Log.e("Loi", e.toString());
        }
        return ds;
    }
}
