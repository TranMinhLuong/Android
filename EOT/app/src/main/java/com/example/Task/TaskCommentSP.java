package com.example.Task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.Adapter.BinhLuanAdapter;
import com.example.Models.CommentSanPham;
import com.example.Models.SanPhamMG;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TaskCommentSP extends AsyncTask<String, Void, ArrayList<CommentSanPham>> {

    @Override
    protected ArrayList<CommentSanPham> doInBackground(String... strings) {
        ArrayList<CommentSanPham> ds = new ArrayList<>();
        String id = strings[0];
        try {
            URL url = new URL("http://172.20.10.3/PHPCK/API/ItemProduct/"+id);
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
                CommentSanPham commentSanPham = new CommentSanPham();

                commentSanPham.setTenkh(item.getString("tenkh"));
                commentSanPham.setCreated(item.getString("created_at"));
                commentSanPham.setComment(item.getString("comment"));
                commentSanPham.setRating(item.getString("rating"));


                ds.add(commentSanPham);
                Log.d("JSONSP", json);
            }
        }catch (Exception e){
            Log.e("Loi", e.toString());
        }
        return ds;
    }
}
