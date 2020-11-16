package com.example.applicationeot.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationeot.Adapter.BinhLuanAdapter;
import com.example.applicationeot.Models.BinhLuanMG;
import com.example.applicationeot.Models.GioHang;
import com.example.applicationeot.Models.SanPhamMG;
import com.example.applicationeot.R;
import com.example.applicationeot.Task.TaskBinhLuan;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChiTietSPActivity extends AppCompatActivity {
    private RecyclerView recyclerbl;
    private Toolbar toolbarctsp;
    private BinhLuanAdapter binhLuanAdapter;
    private Spinner spinnerctsp;
    private Button btnthemgh, btnsendcomment;
    private ImageView imghinhctsp;
    private TextView txtTenctsp, txtGiactsp, txtMotactsp, txtNCCctsp;
    private EditText edttencomment, edtcomment;
    private RatingBar addrating;
    SanPhamMG sanPhamMG;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);

        addControls();
        addEvents();
        EventSpinner();
    }

    private void EventSpinner() {
        Integer[] number = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item,number);
        spinnerctsp.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menucart:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Gson gson = new Gson();
//        SharedPreferences preferences = getSharedPreferences("GioHang", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        String json = gson.toJson(MainActivity.dsgiohang);
//        editor.putString("GioHang",json);
//        editor.commit();
//    }

    private void addEvents() {
        toolbarctsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnthemgh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int giamgia = (int) ((1 - (int) sanPhamMG.getKhuyenmai()*1.0/100) * Integer.parseInt(sanPhamMG.getGiasp()));
                if (MainActivity.dsgiohang.size() > 0) {
                    int sl = Integer.parseInt(spinnerctsp.getSelectedItem().toString());
                    for (int i = 0; i < MainActivity.dsgiohang.size(); i++) {
                        if (MainActivity.dsgiohang.get(i).getIdsp() == sanPhamMG.getIdsp()) {
                            MainActivity.dsgiohang.get(i).setSoluongsp(MainActivity.dsgiohang.get(i).getSoluongsp() + sl);
                            Toast.makeText(ChiTietSPActivity.this, "id:" + sanPhamMG.getIdsp(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(ChiTietSPActivity.this, "idgh:" + MainActivity.dsgiohang.get(i).getIdsp(), Toast.LENGTH_SHORT).show();
                            if (MainActivity.dsgiohang.get(i).getSoluongsp() >= 10) {
                                MainActivity.dsgiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.dsgiohang.get(i).setGiaspgh(String.valueOf(giamgia * MainActivity.dsgiohang.get(i).getSoluongsp()));
                            return;
                        }
                    }
                    String giamoi = String.valueOf(giamgia * sl);
                    MainActivity.dsgiohang.add(new GioHang(sanPhamMG.getIdsp(), sanPhamMG.getTensp(), giamoi, sanPhamMG.getUlrimage(), sanPhamMG.getImghinhsp(), sl, sanPhamMG.getKhuyenmai()));
                    Toast.makeText(getApplicationContext(),"Add Success",Toast.LENGTH_LONG).show();
                } else {
                    int soluong = Integer.parseInt(spinnerctsp.getSelectedItem().toString());
                    String giamoi = String.valueOf(giamgia * soluong);
                    MainActivity.dsgiohang.add(new GioHang(sanPhamMG.getIdsp(), sanPhamMG.getTensp(), giamoi, sanPhamMG.getUlrimage(), sanPhamMG.getImghinhsp(), soluong, sanPhamMG.getKhuyenmai()));
                    Toast.makeText(getApplicationContext(),"Add Success",Toast.LENGTH_LONG).show();
                }
//                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
//                startActivity(intent);
            }
        });
       btnsendcomment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SendComment();
           }
       });
    }

    private void SendComment() {
        String idsp = String.valueOf(sanPhamMG.getIdsp());
        Log.d("ABCD", idsp);
        String tenkh = edttencomment.getText().toString().trim();
        Log.d("ABCD", tenkh);
        String comment = edtcomment.getText().toString().trim();
        Log.d("ABCD", comment);
        String rating = String.valueOf(addrating.getRating());
        Log.d("ABCD", rating);
//        binhLuanMG.setIdsp(idsp);
//        binhLuanMG.setTenkh(tenkh);
//        binhLuanMG.setComment(comment);
//        binhLuanMG.setRating(rating);
        if (idsp.isEmpty() || tenkh.isEmpty() || comment.isEmpty() || rating.isEmpty()){
            Toast.makeText(getApplicationContext(), "Vui lòng nhập đủ yêu cầu!", Toast.LENGTH_SHORT).show();
        }else {
            PostComment postComment = new PostComment();
            postComment.execute(idsp, tenkh, comment, rating);
            BinhLuanShow binhLuanShow = new BinhLuanShow();
            binhLuanShow.execute(idsp);
        }
        edtcomment.setText("");
        edttencomment.setText("");
        addrating.setRating(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        sanPhamMG = (SanPhamMG) intent.getSerializableExtra("sanpham");
        sanPhamMG.setImghinhsp((Bitmap) intent.getParcelableExtra("img"));
        imghinhctsp.setImageBitmap(sanPhamMG.getImghinhsp());
        txtTenctsp.setText(sanPhamMG.getTensp()+"("+sanPhamMG.getKhuyenmai()+"%)");
        int giamgia = (int) ((1 - (int) sanPhamMG.getKhuyenmai()*1.0/100) * Integer.parseInt(sanPhamMG.getGiasp()));
        txtGiactsp.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(giamgia) +" VND");
        txtMotactsp.setText(sanPhamMG.getNoidung());
        txtNCCctsp.setText(sanPhamMG.getNhacungcap());
        BinhLuanShow binhLuanShow = new BinhLuanShow();
        binhLuanShow.execute(String.valueOf(sanPhamMG.getIdsp()));
    }

    private void addControls() {
        toolbarctsp = findViewById(R.id.toolbarctsp);
        setSupportActionBar(toolbarctsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnthemgh = findViewById(R.id.btnthemgh);
        imghinhctsp = findViewById(R.id.imgctsp);
        txtTenctsp = findViewById(R.id.txtTenctsp);
        txtGiactsp = findViewById(R.id.txtGiactsp);
        txtMotactsp = findViewById(R.id.txtMotactsp);
        txtNCCctsp = findViewById(R.id.txtNCCctsp);
        recyclerbl = findViewById(R.id.recyclerbinhluan);
        spinnerctsp = findViewById(R.id.spinnerctsp);
        edttencomment = findViewById(R.id.edttencomment);
        edtcomment = findViewById(R.id.edtcomment);
        addrating = findViewById(R.id.addrating);
        btnsendcomment = findViewById(R.id.btnsendcomment);
    }

    class BinhLuanShow extends TaskBinhLuan{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<BinhLuanMG> binhLuanMGS) {
            binhLuanAdapter = new BinhLuanAdapter(ChiTietSPActivity.this, binhLuanMGS);
            recyclerbl.setAdapter(binhLuanAdapter);
            recyclerbl.setLayoutManager(new LinearLayoutManager(ChiTietSPActivity.this));
            super.onPostExecute(binhLuanMGS);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    class PostComment extends AsyncTask<String, Void, String>{
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
                URL url = new URL("http://192.168.1.247/PHPCK/API/AddComment/"+strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.setDoOutput(true);
                connection.setDoInput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                Uri.Builder builders = new Uri.Builder()
                        .appendQueryParameter("name", strings[1])
                        .appendQueryParameter("comment", strings[2])
                        .appendQueryParameter("rating", strings[3]);
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

                    Log.d("JSON_PostComment",json);
                    br.close();
                    isr.close();
                    connection.disconnect();
                    return json;
                }
            }catch (Exception e){
                Log.e("Loi", e.toString());
            }
            return null;
        }
    }
}
