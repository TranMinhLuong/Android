package com.example.floatingmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.adapter.SinhVienAdapter;
import com.example.models.SinhVien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<SinhVien> listsv;
    SinhVienAdapter svAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();

        registerForContextMenu(recyclerView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.floating_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void addControls() {
        recyclerView = findViewById(R.id.recyclerview);
        listsv = new ArrayList<>();
        listsv.add(new SinhVien(R.drawable.boruto, "Boruto", "Phong thuật", 1));
        listsv.add(new SinhVien(R.drawable.naruto, "Naruto", "Ảo thuật", 2));
        listsv.add(new SinhVien(R.drawable.itachi, "Itachi", "Thổ thuật", 3));
        listsv.add(new SinhVien(R.drawable.sasuke, "Sasuke", "Kiếm thuật", 4));
        listsv.add(new SinhVien(R.drawable.anh1, "Thêm sinh viên ở dòng này", null, 0));

        svAdapter = new SinhVienAdapter(MainActivity.this, listsv);
        recyclerView.setAdapter(svAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        RecyclerView.LayoutManager layoutManager = manager;
        recyclerView.setLayoutManager(layoutManager);

    }

    private void addEvents() {

    }
}
