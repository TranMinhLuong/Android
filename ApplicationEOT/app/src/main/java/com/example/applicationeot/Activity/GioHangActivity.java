package com.example.applicationeot.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationeot.Adapter.GioHangAdapter;
import com.example.applicationeot.Models.GioHang;
import com.example.applicationeot.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {
    private RecyclerView recyclergiohang;
    private GioHangAdapter gioHangAdapter;
    static TextView txtthongbao, txttongtien;
    private Button btnthanhtoan, btntieptuc;
    private Toolbar toolbargh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        addControls();
        addEvent();
        CheckData();
        EvenUntils();
    }


    public static void EvenUntils() {
        int tongtien = 0;
        for (int i = 0 ; i < MainActivity.dsgiohang.size(); i++){
            tongtien += Integer.parseInt(MainActivity.dsgiohang.get(i).getGiaspgh());
        }
       txttongtien.setText(NumberFormat.getNumberInstance(Locale.getDefault()).format(tongtien) + " VNĐ");
    }

    private void CheckData() {
        if (MainActivity.dsgiohang.size() <= 0){
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            recyclergiohang.setVisibility(View.INVISIBLE);
        }else {
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            recyclergiohang.setVisibility(View.VISIBLE);
        }
    }

    private void addEvent() {
        String SESSION_KEY = "SESSION_USER";
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        final String name = sessionManagement.sharedPreferences.getString(SESSION_KEY, "");
        toolbargh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DanhMucActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.dsgiohang.size() <= 0){
                    Toast.makeText(GioHangActivity.this, "Bạn chưa chọn hàng hóa vui lòng lựa chọn!", Toast.LENGTH_SHORT).show();
                }else {
                    if (name.isEmpty()){
                        Toast.makeText(GioHangActivity.this, "Vui lòng đăng nhập!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void addControls() {
        recyclergiohang = findViewById(R.id.recyclergiohang);
        txtthongbao = findViewById(R.id.txtthongbao);
        txttongtien = findViewById(R.id.txttongtien);
        btnthanhtoan = findViewById(R.id.btnthanhtoan);
        btntieptuc = findViewById(R.id.btnmuatiepsp);
        toolbargh = findViewById(R.id.toolbargh);
        setSupportActionBar(toolbargh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
//        sessionManagement.setSections(MainActivity.dsgiohang);
//        ArrayList<GioHang> gioHangs = sessionManagement.getSections();
        gioHangAdapter = new GioHangAdapter(getApplicationContext(), MainActivity.dsgiohang);
        recyclergiohang.setAdapter(gioHangAdapter);
        recyclergiohang.setLayoutManager(new LinearLayoutManager(this));
        gioHangAdapter.setOnItemClickListener(new GioHangAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(GioHangActivity.this, "Đã loại bỏ sản phẩm: "+MainActivity.dsgiohang.remove(position).getTenspgh(), Toast.LENGTH_SHORT).show();
                gioHangAdapter.notifyDataSetChanged();
                CheckData();
                EvenUntils();
//                Gson gson = new Gson();
//                SharedPreferences preferences = getSharedPreferences("GioHang", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                String json = gson.toJson(MainActivity.dsgiohang);
//                editor.putString("GioHang",json);
//                editor.commit();
            }
        });
    }
}
